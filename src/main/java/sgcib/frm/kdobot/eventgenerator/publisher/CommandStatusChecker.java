package sgcib.frm.kdobot.eventgenerator.publisher;

import org.springframework.scheduling.annotation.Async;
import sgcib.frm.kdobot.eventgenerator.model.CommandStatus;
import sgcib.frm.kdobot.eventgenerator.model.CommandStatusParameter;
import sgcib.frm.kdobot.eventgenerator.model.ResultWrapper;

public class CommandStatusChecker {

    public ResultWrapper<CommandStatus> checkCommandStatusUntilFinished(CommandStatusParameter commandStatusParameter){
        return new ResultWrapper<>(new CommandStatus(), "OK", "The check completed successfuly");
    }
}
