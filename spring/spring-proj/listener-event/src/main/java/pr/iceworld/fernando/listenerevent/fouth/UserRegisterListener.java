package pr.iceworld.fernando.listenerevent.fouth;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 用户注册监听器
 */
@Component
public class UserRegisterListener {

    @EventListener
    @Order(1)
    public void sendMail(UserRegisterEvent event) {
        System.out.println(String.format("【%s】，给用户【%s】发送注册成功邮件!", Thread.currentThread(), event.getUserName()));
    }
 
    @EventListener
    @Order(10)
    public void sendCoupon(UserRegisterEvent event) {
        System.out.println(String.format("【%s】，给用户【%s】发送优惠券!", Thread.currentThread(), event.getUserName()));
    }
}