package sgcib.frm.kdobot.eventgenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sgcib.frm.kdobot.eventgenerator.events.CommandFinishedEvent;
import sgcib.frm.kdobot.eventgenerator.events.CommandLateEvent;
import sgcib.frm.kdobot.eventgenerator.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommandsService {
    private DataHolder dataHolder;
    private final ApplicationEventPublisher publisher;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsService.class);

    @Autowired
    public CommandsService(DataHolder dataHolder, ApplicationEventPublisher publisher) {
        this.dataHolder = dataHolder;
        this.publisher = publisher;
    }

    @Scheduled(fixedDelayString = "${scheduled.commands.delay}")
    public void updateMonitoredCommands() {
        LOGGER.info("Updating commands");
        Set<Command> commands = new HashSet<>();
        //
        // DO stuff
        //
        ConcurrentMap<String, Command> commandByName =
                commands.stream().collect(Collectors.toConcurrentMap(Command::getName, Function.identity()));
        this.dataHolder.setCommandsByName(commandByName);
    }

    @Scheduled(fixedDelayString = "${scheduled.running-commands.delay}")
    public void updateRunningCommands() {
        LOGGER.info("Updading running commands");
        Collection<Command> commands = this.dataHolder.getCommandsByName().values();
        Set<RunningCommandParameter> parameters = commands.stream().map((command) -> new RunningCommandParameter())
                .collect(Collectors.toSet());
        Set<RunningCommand> runningCommands = new HashSet<>(); // get from server
        //
        ConcurrentMap<String, RunningCommand> runningCommandByJobId =
                runningCommands.stream().collect(Collectors.toConcurrentMap(RunningCommand::getJobId, Function.identity()));

        this.dataHolder.setRunningCommandByJobId(runningCommandByJobId);
    }

    @Scheduled(fixedDelayString = "${scheduled.commands-status.delay}")
    public void updateCommandsStatus() {
        LOGGER.info("Updating command statuses");
        Collection<RunningCommand> runningCommands = this.dataHolder.getRunningCommandByJobId().values();
        Set<CommandStatusParameter> parameters =
                runningCommands.stream().map((runningCmd) -> new CommandStatusParameter()).collect(Collectors.toSet());
        // pull status
        Set<CommandStatus> commandStatuses = new HashSet<>(); // get from server

        // check
        for (CommandStatus commandStatus : commandStatuses) {
            if(commandStatus.isFinished()){
                this.publisher.publishEvent(new CommandFinishedEvent(null, commandStatus));
                this.dataHolder.getRunningCommandByJobId().remove(commandStatus.getJobId());
            }

            if(commandStatus.isLate()){
                this.publisher.publishEvent(new CommandLateEvent(null, commandStatus));
            }

        }

        //store
        ConcurrentMap<String, CommandStatus> commandStatusByJobId =
                commandStatuses.stream().collect(Collectors.toConcurrentMap(CommandStatus::getJobId, Function.identity()));
        this.dataHolder.setCommandStatusByJobId(commandStatusByJobId);

    }
}
