package pr.iceworld.fernando.springcloud.openfeign02;

public class AliyunSmsService implements SmsService {
    @Override
    public void send(String phone, String content) {
        System.out.printf("AliyunSms send to %s content %s used aliyun sms%n", phone, content);
    }
}