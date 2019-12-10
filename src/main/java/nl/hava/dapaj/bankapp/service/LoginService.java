/*
package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public boolean validatePassword (String userName, String password){
        User user = userDao.findUserByName(userName);
        if(user == null){
            return false;
        }else if (user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
*/
