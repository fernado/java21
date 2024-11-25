package pr.iceworld.fernando.demo.model;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("userFactory")
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        // help us create complex object
        // see Mybatis SqlSessionFactorybean
        return new User("Fernando", 23);
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
