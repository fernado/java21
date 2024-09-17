package pr.iceworld.fernando.springcloud.openfeign01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyDefaultConfig {

    @Bean
    public Person2 demo() {
        return new Person2("default", "男");
    }
}