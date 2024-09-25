package pr.iceworld.fernando.listenerevent.fouth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
 
import java.util.concurrent.Executor;
 
@ComponentScan
@Configuration
public class FourthMainConfig {
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() { //@1
        //创建一个事件广播器
        SimpleApplicationEventMulticaster events = new SimpleApplicationEventMulticaster();
        //给广播器提供一个线程池，通过这个线程池来调用事件监听器
        Executor executor = this.applicationEventMulticasterThreadPool().getObject();
        //设置异步执行器
        events.setTaskExecutor(executor);//@1
        return events;
    }
 
    @Bean
    public ThreadPoolExecutorFactoryBean applicationEventMulticasterThreadPool() {
        ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
        threadPoolExecutorFactoryBean.setThreadNamePrefix("applicationEventMulticasterThreadPool-");
        threadPoolExecutorFactoryBean.setCorePoolSize(5);
        return threadPoolExecutorFactoryBean;
    }
}