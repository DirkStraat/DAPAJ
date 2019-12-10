package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FillDatabaseController {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    SMEAccountDao smeAccountDao;

    @Autowired
    UserDao userDao;

    @GetMapping("fdb")
    public String fillDatabase(){
        Factory fabriekje = new Factory();
        Set<Customer> customers = new HashSet<>();
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Company> companies = new ArrayList<>();
        ArrayList<SMEAccount> smeAccounts = new ArrayList<>();
        int companyEmployeesIndex = 0;
        ArrayList<User> users = new ArrayList<>();

        customers = fabriekje.generateCustomers(4000);

        for (Customer customer : customers) {
            Account account = fabriekje.generateRetailAccount(customer);
            accounts.add(account);
        }

        for (int i = 0; i <1000 ; i++) {
            companies.add(fabriekje.generateCompany());
        }

        for (int i = 0; i <2000 ; i++) {
            users.add(fabriekje.generateUser());
        }

        for (Company company : companies) {
            List<User> companyEmployees = new ArrayList<>();
            companyEmployees.add(users.get(companyEmployeesIndex++));
            companyEmployees.add(users.get(companyEmployeesIndex++));
            company.setCompanyEmployees(companyEmployees);
            smeAccounts.add(fabriekje.generateSMEAccount(company));
        }

        for (Address address : fabriekje.getAddresses()) {
            addressDao.save(address);
        }

        for (Customer customer : customers) {
            addressDao.save(customer.getAddress());
            customerDao.save(customer);
        }

        for (Account account : accounts){
            accountDao.save(account);
        }

        for (User user : users) {
            userDao.save(user);
        }

        for (Company company : companies) {
            companyDao.save(company);
        }

        for (SMEAccount smeAccount : smeAccounts) {
            smeAccountDao.save(smeAccount);
        }

        return "login";
    }
}
