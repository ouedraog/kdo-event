package sgcib.frm.kdobot.eventgenerator.events;

import org.springframework.context.ApplicationEvent;
import sgcib.frm.kdobot.eventgenerator.model.CommandStatus;

import java.util.List;

public class CommandStatusEvent extends ApplicationEvent {

    private final CommandStatus commandStatus;
    private final List<Object> users;

    public CommandStatusEvent(Object source, CommandStatus commandStatus, List<Object>users) {
        super(source);
        this.commandStatus = commandStatus;
        this.users = users;
    }

    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    public List<Object> getUsers() {
        return users;
    }
}
