package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Customer;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    @Autowired
    private UserDao userDao;


    public User findUserByCustomerId(int customerId) {
        return userDao.findUserByCustomerId(customerId);
    }

    public User findUserByLoginName(String name) {
      return  userDao.findUserByLoginName(name);
    }

    public User findUserBySocialSecurityNumber(String socialSecurityNumber) {
        return userDao.findUserBySocialSecurityNumber(socialSecurityNumber);
    }

    public List<Customer> findCustomersByAccountId(Account id){
        return userDao.findCustomerByaccounts(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

}
