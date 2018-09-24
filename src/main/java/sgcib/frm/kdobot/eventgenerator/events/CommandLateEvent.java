package sgcib.frm.kdobot.eventgenerator.events;

import org.springframework.context.ApplicationEvent;
import sgcib.frm.kdobot.eventgenerator.model.CommandStatus;

public class CommandLateEvent extends ApplicationEvent {

    private final CommandStatus commandStatus;

    public CommandLateEvent(Object source, CommandStatus commandStatus) {
        super(source);
        this.commandStatus = commandStatus;
    }

    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

}
