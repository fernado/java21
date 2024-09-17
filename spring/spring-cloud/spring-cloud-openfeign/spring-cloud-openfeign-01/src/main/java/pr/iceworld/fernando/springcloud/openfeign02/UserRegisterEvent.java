package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class UserRegisterEvent extends ApplicationEvent {
    private String userName;
    private String password;
    private String mobile;

    public UserRegisterEvent(Object source, String userName, String password, String mobile) {
        super(source);
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
    }

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }
}
