package sgcib.frm.kdobot.eventgenerator.events;

import org.springframework.context.ApplicationEvent;
import sgcib.frm.kdobot.eventgenerator.model.CommandStatus;

public class CommandFinishedEvent extends ApplicationEvent {

    private final CommandStatus commandStatus;

    public CommandFinishedEvent(Object source, CommandStatus commandStatus) {
        super(source);
        this.commandStatus = commandStatus;
    }

    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

}
