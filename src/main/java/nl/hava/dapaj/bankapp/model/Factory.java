package nl.hava.dapaj.bankapp.model;

import com.github.javafaker.Faker;
import nl.hava.dapaj.bankapp.service.TransactionService;
import nl.hava.dapaj.bankapp.utils.IBANGenerator;

import java.util.*;


public class Factory {
    static final int NUMBER_OF_CITIES = 1;
    static final int NUMBER_OF_STREETS = 20;
    static final int NUMBER_OF_HOUSES = 20;
    static final int NUMBER_OF_IBANS = 100;

    private Faker faker;
    private TransactionService transactionService;
    private List<Address> addresses;
    private List<String> iBans;
    private List<Employee> bankEmployees;


    public Factory(){
        this.faker = new Faker(new Locale("nl"));
        this.transactionService = new TransactionService();
        this.addresses = new ArrayList<>();
        this.iBans = new ArrayList<>();
        this.bankEmployees = new ArrayList<>();
        generateAddresses();
        generateIbans();
        generateEmployees();
    }

    private String createPrefix(){
        int prefixCase = (int)(Math.random()*4);
        switch (prefixCase){
            case 0: return null;
            case 1: return "van";
            case 2: return "van der";
            case 3: return "den";
            case 4: return "van den";
            default: return "toevalbestaatniet";
        }
    }

    public User generateUser(){
        String firstName = faker.name().firstName();
        String prefix = this.createPrefix();
        String lastName = faker.name().lastName();
        Address address = pickRandom(addresses);
        String socialSecurityNumber = faker.idNumber().ssnValid();
        Date dateOfBirth = faker.date().birthday(18, 83);
        String email = String.format(firstName + lastName + "@example.com");
        User user = new User(firstName, prefix, lastName, address, socialSecurityNumber, dateOfBirth, email);
        user.setPassword(faker.funnyName().name());
        return user;
    }

    private Customer generateCustomer(){
        User user = generateUser();
        Customer customer = new Customer(user.firstName, user.prefix, user.lastName, user.address,
                user.socialSecurityNumber, user.dateOfBirth, user.email);
        customer.setPassword(user.getPassword());
        return customer;
    }

    private Employee generateEmployee(String role){
        Customer employeeAsCustomer = generateCustomer();
        Employee employee = new Employee(role, employeeAsCustomer);
        return employee;
    }

    public Set<Customer> generateCustomers(int amount){
        Set<Customer>  customers = new HashSet<>();

        for (int i = 0; i <amount ; i++) {
            Customer customer = this.generateCustomer();
            customers.add(customer);
        }

        return customers;
    }

    private void generateAddresses(){
        String country = "Nederland";

        for (int i = 0; i <NUMBER_OF_CITIES ; i++) {
            String city = faker.address().city();
            for (int j = 0; j <NUMBER_OF_STREETS ; j++) {
                String street = faker.address().streetName();
                String postcode = faker.address().zipCode();
                for (int k = 0; k <NUMBER_OF_HOUSES ; k++) {
                    int housenumber = k+1;
                    Address address = new Address(street, housenumber, postcode, city, country);
                    addresses.add(address);
                }
            }
        }
    }

    public Company generateCompany(){
        String companyName = faker.company().name();
        Address address = pickRandom(addresses);
        Company company = new Company(companyName, address);
        return company;
    }

    private <T> T pickRandom(List<T> list){
        int size = list.size();
        int randomIndex = (int)(Math.random()*size);
        T t = list.get(randomIndex);
        list.remove(randomIndex);
        return t;
    }

    private void generateIbans(){
        iBans = new ArrayList<>();
        for (int i = 0; i <NUMBER_OF_IBANS ; i++) {
            iBans.add(IBANGenerator.generateIBAN());
        }
    }

    public Account generateRetailAccount(Customer customer){
        String iBan = pickRandom(this.iBans);
        Account account = new Account(iBan);
        account.setAccountName(String.format(customer.getFirstName()+customer.getLastName()));
        account.addCustomer(customer);
        customer.addAccount(account);
        return account;
    }

    public SMEAccount generateSMEAccount(Company company){
        String iBan = pickRandom(this.iBans);
        String sector = faker.company().industry();
        SMEAccount smeAccount = new SMEAccount(iBan, sector, bankEmployees.get(1), company);
        return smeAccount;
    }


    private void generateEmployees(){
        bankEmployees = new ArrayList<>();
        Set<Customer> customers = generateCustomers(2);
        List<Customer> customerList = new ArrayList<>();
        customerList.addAll(customers);
        Employee managerRetail = new Employee("Manager Retail", customerList.get(0));
        Employee managerSME = new Employee("Manager SME", customerList.get(1));
        bankEmployees.add(managerRetail);
        bankEmployees.add(managerSME);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Employee> getBankEmployees() {
        return bankEmployees;
    }

    public void transactionfactory(int numberOfTransactions, List<Account> accounts){
        Account debitAccount = accounts.get((int)(Math.random()*accounts.size()));
        Account creditAccount = accounts.get((int)(Math.random()*accounts.size()));
        if (creditAccount.getAccountID() == debitAccount.getAccountID()){
            creditAccount = accounts.get((int)(Math.random()*accounts.size()));
        }
        double amount =  (Math.random()*10000)/100;
        String description = faker.funnyName().name();

        transactionService.doTransAction(debitAccount, creditAccount, amount, description);
    }
}
