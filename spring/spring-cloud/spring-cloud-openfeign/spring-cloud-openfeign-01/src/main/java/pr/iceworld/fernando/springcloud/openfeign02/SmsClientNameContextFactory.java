package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.cloud.context.named.NamedContextFactory;

public class SmsClientNameContextFactory extends NamedContextFactory<SmsClientSpecification> {
 
    public SmsClientNameContextFactory() {
        super(DefaultSmsClientConfiguration.class, "sms", "sms.client.name");
    }
 
    public SmsService getSmsService(String serviceName) {
        return getInstance(serviceName, SmsService.class);
    }
}