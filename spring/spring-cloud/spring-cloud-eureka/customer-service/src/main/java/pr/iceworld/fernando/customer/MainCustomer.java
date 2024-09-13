package pr.iceworld.fernando.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainCustomer {

    public static void main(String[] args) {
        SpringApplication.run(MainCustomer.class, args);
    }
}
