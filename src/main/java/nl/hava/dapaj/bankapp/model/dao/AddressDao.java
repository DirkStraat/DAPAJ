package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressDao extends CrudRepository<Address, Integer> {

}
