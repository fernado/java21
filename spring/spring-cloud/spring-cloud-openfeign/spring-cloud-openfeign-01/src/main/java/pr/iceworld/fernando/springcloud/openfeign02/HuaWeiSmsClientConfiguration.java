package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class HuaWeiSmsClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SmsService smsService() {
       return new HuaWeiSmsService();
    }
}