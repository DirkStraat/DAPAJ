package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends CrudRepository<Account, Integer> {
}
