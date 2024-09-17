package pr.iceworld.fernando.springcloud.openfeign02.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pr.iceworld.fernando.springcloud.openfeign02.model.Customer;
import pr.iceworld.fernando.springcloud.openfeign02.model.Data;

@FeignClient(value = "customer", fallbackFactory = CustomerClientFallbackFactory.class)
public interface CustomerClient {

    @GetMapping("/customer/{id}")
    Data<Customer> getCustomer(@PathVariable("id") Long id);
}
