package pr.iceworld.fernando.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MainEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(MainEurekaServer.class, args);
    }
}
