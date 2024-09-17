package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;


public class AliyunSmsClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SmsService smsService() {
       return new AliyunSmsService();
    }
}