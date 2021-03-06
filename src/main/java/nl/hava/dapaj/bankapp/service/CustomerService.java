package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Customer;
import nl.hava.dapaj.bankapp.model.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> findCustomerBySocialSecurityNumber(String socialSecurityNumber) {
        return customerDao.findCustomerBySocialSecurityNumber(socialSecurityNumber);
    }
}
