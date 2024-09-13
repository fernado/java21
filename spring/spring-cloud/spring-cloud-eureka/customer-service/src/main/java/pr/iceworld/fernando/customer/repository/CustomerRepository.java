package pr.iceworld.fernando.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pr.iceworld.fernando.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
