package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer > {
    List<Transaction> getTransactionsByCreditAccount(Account account);
    List<Transaction> getTransactionsByDebitAccount(Account account);
}
