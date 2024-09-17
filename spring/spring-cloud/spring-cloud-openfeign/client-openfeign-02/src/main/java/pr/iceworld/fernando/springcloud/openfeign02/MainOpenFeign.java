package pr.iceworld.fernando.springcloud.openfeign02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MainOpenFeign {
    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign.class, args);
    }
}
