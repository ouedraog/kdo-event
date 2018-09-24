package sgcib.frm.kdobot.eventgenerator.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sgcib.frm.kdobot.eventgenerator.events.CommandFinishedEvent;
import sgcib.frm.kdobot.eventgenerator.events.CommandLateEvent;

@Component
public class EventListenerAndNotifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerAndNotifier.class);

    @EventListener
    public void onCommandFinished(CommandFinishedEvent commandStatusEvent){
        LOGGER.info("Command finished event: "+ commandStatusEvent.getCommandStatus());
        // chase user subscribed via IndianaBot
    }

    @EventListener
    public void onCommandLate(CommandLateEvent commandStatusEvent){
        LOGGER.info("Command late event: "+ commandStatusEvent.getCommandStatus());
        // chase user subscribed via IndianaBot
    }
}
