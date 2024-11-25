package pr.iceworld.fernando.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import pr.iceworld.fernando.demo.model.User;

public class HierachicalBeanFactoryTest {

    @Test
    public void testHierachicalBeanFactory() {
        // create bean factory
        DefaultListableBeanFactory parentBeanFactory = new DefaultListableBeanFactory();
        parentBeanFactory.registerSingleton("user", new User("fernando", 42));
        DefaultListableBeanFactory childBeanFactory = new DefaultListableBeanFactory();
        childBeanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("childBeanFactory ------------");
        System.out.println(childBeanFactory.containsLocalBean("user"));
        System.out.println(childBeanFactory.containsSingleton("user"));
        System.out.println(childBeanFactory.containsBean("user"));
        System.out.println(childBeanFactory.getBean("user"));
        System.out.println("parentBeanFactory ------------");

        System.out.println(parentBeanFactory.containsLocalBean("user"));
        System.out.println(parentBeanFactory.containsSingleton("user"));
        System.out.println(parentBeanFactory.containsBean("user"));
        System.out.println(parentBeanFactory.getBean("user"));


    }
}
