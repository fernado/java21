package pr.iceworld.fernando.springcloud.openfeign02;

public class DefaultSmsService implements SmsService {
    @Override
    public void send(String phone, String content) {
        System.out.printf("DeafultSms send to %s content %s used default sms%n", phone, content);
    }
}