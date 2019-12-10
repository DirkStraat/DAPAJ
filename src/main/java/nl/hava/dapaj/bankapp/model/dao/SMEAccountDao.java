package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.SMEAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMEAccountDao extends CrudRepository <SMEAccount, Integer> {
}
