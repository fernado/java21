package pr.iceworld.fernando.listenerevent.third;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用户注册成功发送邮件
 */
@Component
@Order(10)
public class SendCouponListener implements ApplicationListener<UserRegisterEvent> {
 
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(String.format("准备给用户【%s】发送优惠券，模拟业务处理，触发时间【%s】", event.getUserName(), LocalDateTime.now()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("给用户【%s】发送优惠券!触发时间【%s】", event.getUserName(), LocalDateTime.now()));
    }
}