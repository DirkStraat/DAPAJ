package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.SMEAccount;
import nl.hava.dapaj.bankapp.model.dao.SMEAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMEAccountService {
    @Autowired
    SMEAccountDao smeAccountDao;

    public SMEAccount getSMEAccountByAccountId(int accountId){
        return smeAccountDao.getSMEAccountByAccountID(accountId);
    }

}
