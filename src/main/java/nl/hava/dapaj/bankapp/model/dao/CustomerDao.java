package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends CrudRepository <Customer, Integer> {
    List<Customer> findCustomerBySocialSecurityNumber(String socialSecurityNumber);
}
