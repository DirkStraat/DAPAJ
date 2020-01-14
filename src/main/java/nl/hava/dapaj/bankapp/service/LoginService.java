package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Employee;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.EmployeeDao;
import nl.hava.dapaj.bankapp.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmployeeDao employeeDao;

    public boolean validatePassword (String loginName, String password){
        User user = userDao.findUserByLoginName(loginName);
        if(user == null){
            return false;
        }else if (user.getPassword().equals(password) && user.getLoginName().equals(loginName)){
            return true;
        }else{
            return false;
        }
    }
    public boolean validateEmployeePassword (String employeeLogin, String employeePassword){
        Employee employee = (Employee) employeeDao.findUserByEmployeeLoginName(employeeLogin);
        if (employee == null){
            return false;
        }else if(employee.getEmployeePassword().equals(employeePassword)&& employee.getEmployeeLoginName().equals(employeeLogin)){
            return true;
        }else {
            return false;
        }
    }
}
