package pr.iceworld.fernando.listenerevent.fouth;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用户注册监听器
 */
@Component
public class UserRegisterListener {

    @EventListener
    @Order(1)
    public void sendMail(UserRegisterEvent event) {
        System.out.println(String.format("【%s】，准备给用户【%s】发送注册成功邮件，模拟业务处理，触发时间【%s】", Thread.currentThread(), event.getUserName(), LocalDateTime.now()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("【%s】，给用户【%s】发送注册成功邮件! 触发时间【%s】", Thread.currentThread(), event.getUserName(), LocalDateTime.now()));

        throw new RuntimeException("模拟异常");
    }
 
    @EventListener
    @Order(10)
    public void sendCoupon(UserRegisterEvent event) {
        System.out.println(String.format("【%s】，给用户【%s】发送优惠券，模拟业务处理, 触发时间【%s】", Thread.currentThread(), event.getUserName(), LocalDateTime.now()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(String.format("【%s】，给用户【%s】发送优惠券! 触发时间【%s】", Thread.currentThread(), event.getUserName(), LocalDateTime.now()));
    }
}