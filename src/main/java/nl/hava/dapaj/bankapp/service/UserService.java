package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


    public void save(User user) {
        userDao.save(user);
    }

}
