package pr.iceworld.fernando.listenerevent.one.register;

import pr.iceworld.fernando.listenerevent.one.event.AbstractEvent;

/**
 * 用户注册成功事件
 */
public class UserRegisterSuccessEvent extends AbstractEvent {
    //用户名
    private String userName;
 
    /**
     * 创建用户注册成功事件对象
     *
     * @param source   事件源
     * @param userName 当前注册的用户名
     */
    public UserRegisterSuccessEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }
 
    public String getUserName() {
        return userName;
    }

}