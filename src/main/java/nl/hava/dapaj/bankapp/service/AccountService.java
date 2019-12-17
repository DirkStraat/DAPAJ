package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.model.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Account getAccountByAccountId(int accountId){
        return accountDAO.findAccountByAccountID(accountId);
    }

    public List<Account> getAccountByCompany(User user) {
        List<Account> accountList = new ArrayList<>();
        Set <Company> companySet =  user.getCompanies();
        for (Company company:companySet){
            Set<SMEAccount> smeAccountSet = company.getAccounts();
            for (SMEAccount account: smeAccountSet){
                    accountList.add(account);
            }
        }
        return accountList;
    }

    public List<Account> getAccountByUser(User user) {
        List<Account> accountList = new ArrayList<>();
        if (user instanceof Customer) {
            for (Account account : ((Customer) user).getAccounts())
                accountList.add(account);
        }
        return accountList;
    }
}
