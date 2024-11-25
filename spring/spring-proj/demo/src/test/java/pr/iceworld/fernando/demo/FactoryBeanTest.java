package pr.iceworld.fernando.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.demo.model.UserFactoryBean;
import pr.iceworld.fernando.demo.model.config.ProjConfig;

public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjConfig.class);
        BeanFactory beanFactory = context.getBeanFactory();
        Object obj = beanFactory.getBean("userFactory");
        Object objFactory = beanFactory.getBean("&userFactory");
        Object objFactoryFromClazz = beanFactory.getBean(UserFactoryBean.class);
        System.out.println(obj);
        System.out.println(objFactory);
        System.out.println(objFactoryFromClazz);
    }
}
