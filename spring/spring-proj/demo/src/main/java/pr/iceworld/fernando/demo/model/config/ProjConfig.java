package pr.iceworld.fernando.demo.model.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.demo.model.User;

@Configuration
@ComponentScan("pr.iceworld.fernando.demo")
public class ProjConfig {

    @Bean
    public User user() {
        return new User("Fernando", 42);
    }
}
