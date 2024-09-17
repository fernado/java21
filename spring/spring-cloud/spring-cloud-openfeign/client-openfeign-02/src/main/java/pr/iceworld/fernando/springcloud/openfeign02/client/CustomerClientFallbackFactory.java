package pr.iceworld.fernando.springcloud.openfeign02.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import pr.iceworld.fernando.springcloud.openfeign02.model.Customer;
import pr.iceworld.fernando.springcloud.openfeign02.model.Data;

public class CustomerClientFallbackFactory implements FallbackFactory<CustomerClient> {
    @Override
    public CustomerClient create(Throwable cause) {
        return new CustomerClient() {

            @Override
            public Data<Customer> getCustomer(Long id) {
                return null;
            }
        };
    }
}
