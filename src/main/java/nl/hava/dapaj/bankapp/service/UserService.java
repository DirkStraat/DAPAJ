package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public User findUserByCustomerId(int customerId) {
        return userDAO.findUserByCustomerId(customerId);
    }

    public List<User> getUserList() {
        return userDAO.getUserList();
    }


    public void save(User user) {
        userDAO.save(user);
    }
}
