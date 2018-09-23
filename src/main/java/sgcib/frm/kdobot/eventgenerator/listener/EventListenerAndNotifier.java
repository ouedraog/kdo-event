package sgcib.frm.kdobot.eventgenerator.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sgcib.frm.kdobot.eventgenerator.events.CommandStatusEvent;

@Component
public class EventListenerAndNotifier {

    @EventListener
    public void onCommandStatusAvailable(CommandStatusEvent commandStatusEvent){
        // chase user subscribed via IndianaBot
    }

}
