package pr.iceworld.fernando.listenerevent.one.register;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class UserRegisterMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RegisterConfig.class);

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        //获取用户注册服务
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        //模拟用户注册
        userRegisterService.registerUser("张三");
    }
}
