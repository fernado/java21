package pr.iceworld.fernando.springcloud.openfeign02;

public class HuaWeiSmsService implements SmsService {
    @Override
    public void send(String phone, String content) {
        System.out.printf("HuaWeiSms send to %s content %s used huawei sms%n", phone, content);
    }
}