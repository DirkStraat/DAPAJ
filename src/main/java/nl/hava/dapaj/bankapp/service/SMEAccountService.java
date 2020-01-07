package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.SMEAccount;
import nl.hava.dapaj.bankapp.model.dao.SMEAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SMEAccountService {
    @Autowired
    SMEAccountDao smeAccountDao;

    public SMEAccount getSMEAccountByAccountId(int accountId){
        return smeAccountDao.getSMEAccountByAccountID(accountId);
    }
    public List<SMEAccount> findAllSmeAccounts() {
        return smeAccountDao.findAll();
    }
    public List<SMEAccount> findsSmeAccountbyBranch(String branch) { return smeAccountDao.findSMEAccountsByBranch(branch);
    }
}
