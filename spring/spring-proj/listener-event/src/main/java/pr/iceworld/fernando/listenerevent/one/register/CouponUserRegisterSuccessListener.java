package pr.iceworld.fernando.listenerevent.one.register;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pr.iceworld.fernando.listenerevent.one.event.EventListener;

/**
 * 用户注册成功事件监听器->负责给用户发送邮件
 */
@Component
@Order(10)
public class CouponUserRegisterSuccessListener implements EventListener<UserRegisterSuccessEvent> {
    @Override
    public void onEvent(UserRegisterSuccessEvent event) {
        System.out.println(
                String.format("给用户【%s】更新优惠券!", event.getUserName()));
    }
}