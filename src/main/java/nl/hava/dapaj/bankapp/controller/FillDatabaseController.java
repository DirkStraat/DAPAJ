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
    final static int AANTAL_KLANTEN = 40;
    final static int AANTAL_BEDRIJVEN = 10;
    final static int AANTAL_GEBRUIKERS = 20;


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
        Set<Customer> customers;
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Company> companies = new ArrayList<>();
        ArrayList<SMEAccount> smeAccounts = new ArrayList<>();
        List<Employee> bankEmployees = fabriekje.getBankEmployees();
        int companyEmployeesIndex = 0;
        ArrayList<User> users = new ArrayList<>();

        customers = fabriekje.generateCustomers(AANTAL_KLANTEN);

        for (Customer customer : customers) {
            Account account = fabriekje.generateRetailAccount(customer);
            accounts.add(account);
        }

        for (int i = 0; i <AANTAL_BEDRIJVEN ; i++) {
            companies.add(fabriekje.generateCompany());
        }

        for (int i = 0; i <AANTAL_GEBRUIKERS ; i++) {
            users.add(fabriekje.generateUser());
        }

        for (Company company : companies) {
            List<User> companyEmployees = new ArrayList<>();
            User companyEmployee = users.get(companyEmployeesIndex++);
            User companyEmployee2 = users.get(companyEmployeesIndex++);
            companyEmployee.addCompany(company);
            companyEmployee2.addCompany(company);
            companyEmployees.add(companyEmployee);
            companyEmployees.add(companyEmployee2);
            company.setCompanyEmployees(companyEmployees);
            smeAccounts.add(fabriekje.generateSMEAccount(company));
        }

        for (Company company : companies) {
            addressDao.save(company.getAddress());
            companyDao.save(company);
            for (User user : company.getCompanyEmployees()) {
                addressDao.save(user.getAddress());
                userDao.save(user);
            }
        }

        for (User user : users) {
            addressDao.save(user.getAddress());
            userDao.save(user);
        }

        for (Customer customer : customers) {
            addressDao.save(customer.getAddress());
            customerDao.save(customer);
        }

        for (Account account : accounts) {
            accountDao.save(account);
        }

        for (Employee employee: bankEmployees) {
            addressDao.save(employee.getAddress());
            employeeDao.save(employee);
        }

        for (SMEAccount smeAccount : smeAccounts) {
            smeAccountDao.save(smeAccount);
        }

        //fabriekje.transactionfactory(300, accounts);

        return "login";
    }
}
