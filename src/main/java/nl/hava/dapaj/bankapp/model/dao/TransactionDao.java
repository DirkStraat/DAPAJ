package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer > {
}