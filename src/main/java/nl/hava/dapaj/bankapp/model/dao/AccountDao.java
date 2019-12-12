package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends CrudRepository<Account, Integer> {
    List<Account> getAccountByCustomersCustomerId(int customerId);

    Account findAccountByAccountID(int accountId);
}
