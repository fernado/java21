package pr.iceworld.fernando.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pr.iceworld.fernando.customer.entity.Customer;
import pr.iceworld.fernando.customer.exception.RecordNotFoundException;
import pr.iceworld.fernando.customer.repository.CustomerRepository;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> {
            return new RecordNotFoundException("");
        });
    }


}
