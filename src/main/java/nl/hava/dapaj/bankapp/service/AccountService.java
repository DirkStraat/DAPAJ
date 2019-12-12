package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDAO;

    public List<Account> getAccountByUserId(int customerId) {
        return accountDAO.getAccountByCustomersCustomerId(customerId);
    }

    public Account getAccountByIban(String iBan){
        return accountDAO.getAccountByIban(iBan);
    }

    public void save(Account account) {
        accountDAO.save(account);
    }
}
