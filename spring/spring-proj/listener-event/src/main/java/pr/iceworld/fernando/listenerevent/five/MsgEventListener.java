package pr.iceworld.fernando.listenerevent.five;

import org.springframework.context.ApplicationListener;

public class MsgEventListener implements ApplicationListener<RegisterEvent> {
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        System.out.println(event.getSource() + " " + Thread.currentThread().getName() + " Msg Send...");
    }


}
