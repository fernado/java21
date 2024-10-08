package pr.iceworld.fernando.listenerevent.third;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用户注册成功发送邮件
 */
@Component
@Order(1)
public class SendEmailListener implements ApplicationListener<UserRegisterEvent> {
 
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(String.format("准备给用户【%s】发送注册成功邮件，模拟业务处理，触发时间【%s】", event.getUserName(), LocalDateTime.now()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("给用户【%s】发送注册成功邮件! 触发时间【%s】", event.getUserName(), LocalDateTime.now()));

        throw new RuntimeException("模拟异常");
    }
}