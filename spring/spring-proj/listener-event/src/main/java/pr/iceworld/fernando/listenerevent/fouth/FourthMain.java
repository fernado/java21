package pr.iceworld.fernando.listenerevent.fouth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class FourthMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(FourthMainConfig.class);
        context.refresh();

        //获取用户注册服务
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        //模拟用户注册
        userRegisterService.registerUser("张三");


        context.close();
    }
}
