package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.context.annotation.Configuration;

@Configuration
@SmsClients(value = {@SmsClient(name = OrderService.SERVICE_NAME, configuration = AliyunSmsClientConfiguration.class),
        @SmsClient(name = UserService.SERVICE_NAME, configuration = HuaWeiSmsClientConfiguration.class)})
public class SmsClientAutoConfiguration {
}