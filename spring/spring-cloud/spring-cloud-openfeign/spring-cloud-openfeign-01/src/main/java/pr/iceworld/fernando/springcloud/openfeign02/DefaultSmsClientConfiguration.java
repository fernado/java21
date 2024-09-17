package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefaultSmsClientConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public SmsService smsService() {
        return new DefaultSmsService();
    }


    @Bean
    @ConditionalOnMissingBean
    public SmsClientNameContextFactory smsClientNameContextFactory(@Autowired(required = false) List<SmsClientSpecification> smsSpecifications) {
        SmsClientNameContextFactory smsClientNameContextFactory = new SmsClientNameContextFactory();
        smsClientNameContextFactory.setConfigurations(smsSpecifications);
        return smsClientNameContextFactory;
    }
}