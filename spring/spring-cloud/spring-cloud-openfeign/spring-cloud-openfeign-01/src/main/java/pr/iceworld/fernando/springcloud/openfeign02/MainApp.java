package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(MainApp.class, args);

        SmsClientNameContextFactory smsClientNameContextFactory = applicationContext.getBean(SmsClientNameContextFactory.class);
        SmsService smsService = smsClientNameContextFactory.getSmsService(UserService.SERVICE_NAME);
        smsService.send("123456", ".................blablabla");



    }
}
