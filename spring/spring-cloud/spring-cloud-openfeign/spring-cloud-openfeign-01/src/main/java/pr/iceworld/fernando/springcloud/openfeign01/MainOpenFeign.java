package pr.iceworld.fernando.springcloud.openfeign01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@EnableFeignClients
@SpringBootApplication
public class MainOpenFeign {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MainOpenFeign.class, args);
        MyNamedContext myNamedContext = new MyNamedContext();
        myNamedContext.setApplicationContext(applicationContext);

        Person2 onePerson2 = (Person2) myNamedContext.getInstance("one", Person2.class);
        Person2 twoPerson2 = (Person2) myNamedContext.getInstance("two", Person2.class);
        System.out.println(String.format("oneDemo=%s", onePerson2));
        System.out.println(String.format("twoDemo=%s", twoPerson2));
        System.out.println(onePerson2.canEqual(twoPerson2));

        // 给 one 注册一个新的 Person
        myNamedContext.registerBean("one", Person.class);

        Person onePerson = (Person) myNamedContext.getInstance("one", Person.class);
        Person twoPerson = (Person) myNamedContext.getInstance("two", Person.class);
        System.out.println(String.format("onePerson=%s", onePerson));
        System.out.println(String.format("twoPerson=%s", twoPerson));


    }
}
