package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository <Customer, Integer> {
}
