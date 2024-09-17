package pr.iceworld.fernando.springcloud.openfeign02;

public interface SmsService {
 
    void send(String phone, String content);
}
