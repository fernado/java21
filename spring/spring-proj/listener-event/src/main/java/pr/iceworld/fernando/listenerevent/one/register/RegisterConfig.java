package pr.iceworld.fernando.listenerevent.one.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.listenerevent.one.event.EventListener;
import pr.iceworld.fernando.listenerevent.one.event.EventMulticaster;
import pr.iceworld.fernando.listenerevent.one.event.SimpleEventMulticaster;

import java.util.List;
 
@Configuration
@ComponentScan(basePackages = "pr.iceworld.fernando.listenerevent.one")
public class RegisterConfig {
 
    /**
     * 注册一个bean：事件发布者
     *
     * @param eventListeners
     * @return
     */
    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners) { //@1
        EventMulticaster eventPublisher = new SimpleEventMulticaster();
        if (eventListeners != null) {
            eventListeners.forEach(eventPublisher::addEventListener);
        }
        return eventPublisher;
    }
 
    /**
     * 注册一个bean：用户注册服务
     *
     * @param eventMulticaster
     * @return
     */
    @Bean
    public UserRegisterService userRegisterService(EventMulticaster eventMulticaster) { //@2
        UserRegisterService userRegisterService = new UserRegisterService();
        userRegisterService.setEventMulticaster(eventMulticaster);
        return userRegisterService;
    }
}