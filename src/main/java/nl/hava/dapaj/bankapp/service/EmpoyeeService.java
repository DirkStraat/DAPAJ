package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Employee;
import nl.hava.dapaj.bankapp.model.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpoyeeService {

    @Autowired
    EmployeeDao employeeDao;

    public Employee findUserByEmployeeLoginName(String loginName){
        return employeeDao.findUserByEmployeeLoginName(loginName);
    }
}
