package pr.iceworld.fernando.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.*;

public class InstantiationTest {

    @Test
    public void testInstantiation() throws ClassNotFoundException {
        // create a beandefinition
        RootBeanDefinition bd = new RootBeanDefinition();
        bd.setBeanClassName("pr.iceworld.fernando.model.User");
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.addPropertyValue("name", "Fernando");
        pvs.addPropertyValue("age", 25);
        bd.setPropertyValues(pvs);
        bd.resolveBeanClass(Thread.currentThread().getContextClassLoader());

        // create a bean factory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", bd);

        InstantiationStrategy strategyOther = new CglibSubclassingInstantiationStrategy();
        Object userOther = strategyOther.instantiate(bd, "user", beanFactory);
        System.out.println(userOther);

        InstantiationStrategy strategy = new SimpleInstantiationStrategy();
        Object user = strategy.instantiate(bd, "user", beanFactory);
        System.out.println(user);
    }
}
