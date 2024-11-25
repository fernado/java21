package pr.iceworld.fernando.listenerevent.five;

import org.springframework.context.ApplicationEvent;

public class RegisterEvent extends ApplicationEvent {
    public RegisterEvent(Object source) {
        super(source);
    }
}
