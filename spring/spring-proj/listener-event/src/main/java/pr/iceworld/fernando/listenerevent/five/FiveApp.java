package pr.iceworld.fernando.listenerevent.five;

import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiveApp {

    public static void main(String[] args) {
        FiveApp fiveApp = new FiveApp();
        fiveApp.doEvent();

    }

    public void doEvent() {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        multicaster.setTaskExecutor(executor);
        multicaster.addApplicationListener(new MsgEventListener());
        multicaster.addApplicationListener(new EmailEventListener());
        multicaster.addApplicationListener(new MsgEventListener());
        multicaster.addApplicationListener(new MsgEventListener());

        multicaster.multicastEvent(new RegisterEvent(this));
        executor.shutdown();

    }
}
