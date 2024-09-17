package pr.iceworld.fernando.customer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pr.iceworld.fernando.customer.entity.Customer;
import pr.iceworld.fernando.customer.model.Data;
import pr.iceworld.fernando.customer.service.CustomerService;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    public Data<Customer> getCustomer(@PathVariable("id") Long id) {
        return Data.success(
                this.customerService.getCustomerById(id),
                serverPort);
    }
}
