package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.model.dao.*;
import nl.hava.dapaj.bankapp.utils.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FactoryService {
    final static int AANTAL_KLANTEN = 400;
    final static int AANTAL_BEDRIJVEN = 100;
    final static int AANTAL_GEBRUIKERS = 300;
    final static int NUMBER_OF_TRANSACTIONS = 5000;
    private int companyEmployeesIndex = 0;
    private Factory fabriekje;

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

    @Autowired
    TransactionService transactionService;

    public FactoryService() {
        super();
        this.fabriekje = new Factory();
    }

    public void fillDatabase() {
        Set<Customer> customers = fabriekje.generateCustomers(AANTAL_KLANTEN);
        List<Account> accounts = generateAccountsWithCustomers(customers);
        List<Company> companies = generateCompanies(AANTAL_BEDRIJVEN);
        List<Customer> users = generateUsers(AANTAL_GEBRUIKERS);
        List<SMEAccount> smeAccounts = new ArrayList<>();
        List<Employee> bankEmployees = fabriekje.generateBankEmployees();

        for (Company company : companies) {
            addEmployeesToCompany(company, users);
            addCompanyToSMEAccount(smeAccounts, company, bankEmployees);
            saveCompany(company);
        }
        for (Customer customer : customers) {
            saveCustomer(customer);
        }
        for (Account account : accounts) {
            saveAccount(account);
        }
        for (Employee bankEmployee : bankEmployees) {
            saveBankEmployee(bankEmployee);
        }
        for (SMEAccount smeAccount : smeAccounts) {
            saveSMEAccount(smeAccount);
        }

    }

    public void transactions(){
        for (int i = 0; i <NUMBER_OF_TRANSACTIONS ; i++) {
            List<Account> accounts = accountDao.getAccountsByAccountIDAfter(0);

            Transaction transaction = fabriekje.transactionfactory(accounts);
            transactionService.doTransAction(transaction);
        }
    }

    private List<Account> generateAccountsWithCustomers(Set<Customer> customers) {
        List<Account> accounts = new ArrayList<>();
        for (Customer customer : customers) {
            Account account = fabriekje.generateRetailAccount(customer);
            accounts.add(account);
        }
        return accounts;
    }

    private List<Company> generateCompanies(int aantalBedrijven){
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i <aantalBedrijven ; i++) {

            companies.add(fabriekje.generateCompany());

        }
        return companies;
    }


    private List<Customer> generateUsers(int aantalGebruikers){
        Set<Customer> users = new HashSet<>();
        //while(!fabriekje.getAddresses().isEmpty()){
            users = fabriekje.generateCustomers(aantalGebruikers);
        //}
        List<Customer> companyCustomers = new ArrayList<>();
        for (Customer customer :users) {
            companyCustomers.add(customer);
        }
        return companyCustomers;
    }

    private void addEmployeesToCompany(Company company, List<Customer> users) {
       if (users.size()>companyEmployeesIndex+1) {
           List<User> companyEmployees = new ArrayList<>();
           User companyEmployee = users.get(companyEmployeesIndex++);
           User companyEmployee2 = users.get(companyEmployeesIndex++);
           companyEmployee.addCompany(company);
           companyEmployee2.addCompany(company);
           companyEmployees.add(companyEmployee);
           companyEmployees.add(companyEmployee2);
           company.setCompanyEmployees(companyEmployees);
       }
    }

    private List<SMEAccount> addCompanyToSMEAccount(List<SMEAccount> smeAccounts, Company company, List<Employee> bankEmployees){
        smeAccounts.add(fabriekje.generateSMEAccount(company, bankEmployees));
        return smeAccounts;
    }

    private void saveCompany(Company company){
        addressDao.save(company.getAddress());
        companyDao.save(company);
        saveCompanyEmployees(company);
    }

    private void saveCompanyEmployees(Company company) {
        for (User user : company.getCompanyEmployees()) {
            addressDao.save(user.getAddress());
            userDao.save(user);
        }
    }

    public void saveUser(User user){
        addressDao.save(user.getAddress());
        userDao.save(user);
    }

    private void saveCustomer(Customer customer){
        addressDao.save(customer.getAddress());
        customerDao.save(customer);
    }

    private void saveAccount(Account account){
        accountDao.save(account);
    }

    private void saveBankEmployee(Employee employee){
        addressDao.save(employee.getAddress());
        employeeDao.save(employee);
    }

    private void saveSMEAccount(SMEAccount smeAccount){
        smeAccountDao.save(smeAccount);
    }

}
