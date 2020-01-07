package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.SMEAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SMEAccountDao extends CrudRepository <SMEAccount, Integer> {

    SMEAccount findSMEAccountByCompanyCompanyId(int companyId);
    SMEAccount getSMEAccountByAccountID(int accountId);
    List<SMEAccount> findSMEAccountsByBranch (String Branch);
    List <SMEAccount> findAll();
}
