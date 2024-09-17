package pr.iceworld.fernando.springcloud.openfeign02.service;

import org.springframework.stereotype.Service;
import pr.iceworld.fernando.springcloud.openfeign02.client.CustomerClient;
import pr.iceworld.fernando.springcloud.openfeign02.model.Customer;
import pr.iceworld.fernando.springcloud.openfeign02.model.Data;

@Service
public class CustomerService {

    private CustomerClient customerClient;

    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public Data<Customer> getCustomerById(Long id) {
        return this.customerClient.getCustomer(id);
    }
}
