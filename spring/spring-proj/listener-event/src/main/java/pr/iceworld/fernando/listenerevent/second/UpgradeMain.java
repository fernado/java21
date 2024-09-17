package pr.iceworld.fernando.listenerevent.second;

import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

public class UpgradeMain {
    public static void main(String[] args) {
        //创建事件广播器
        ApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        //注册事件监听器
        applicationEventMulticaster.addApplicationListener(new SendEmailOnOrderCreateListener());
        //广播事件订单创建事件
        applicationEventMulticaster.multicastEvent(new OrderCreateEvent(applicationEventMulticaster, 1L));

    }
}
