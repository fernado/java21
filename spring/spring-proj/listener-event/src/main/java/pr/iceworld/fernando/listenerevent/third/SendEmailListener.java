package pr.iceworld.fernando.listenerevent.third;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用户注册成功发送邮件
 */
@Component
public class SendEmailListener implements ApplicationListener<UserRegisterEvent> {
 
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(String.format("给用户【%s】发送注册成功邮件!", event.getUserName()));
     }
}