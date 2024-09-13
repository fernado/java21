package pr.iceworld.fernando.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pr.iceworld.fernando.customer.entity.Customer;
import pr.iceworld.fernando.customer.model.Data;
import pr.iceworld.fernando.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public Data<Customer> getCustomer(@PathVariable("id") Long id) {
        return Data.success(this.customerService.getCustomerById(id));
    }
}
