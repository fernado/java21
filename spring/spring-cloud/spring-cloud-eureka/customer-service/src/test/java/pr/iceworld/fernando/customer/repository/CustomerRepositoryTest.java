package pr.iceworld.fernando.customer.repository;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pr.iceworld.fernando.customer.CustomerTest;
import pr.iceworld.fernando.customer.MainCustomer;
import pr.iceworld.fernando.customer.entity.Customer;


public class CustomerRepositoryTest  extends CustomerTest {

    @Resource
    CustomerRepository customerRepository;

    @Test
    public void testCreatData() {
        Customer customer = Customer.builder()
                .name("fernando")
                .age(42)
                .build();

        customerRepository.save(customer);

        customer = Customer.builder()
                .name("wiki")
                .age(42)
                .build();
        customerRepository.save(customer);


        customer = Customer.builder()
                .name("flower")
                .age(32)
                .build();
        customerRepository.save(customer);
    }
}
