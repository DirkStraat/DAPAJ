package nl.hava.dapaj.bankapp.utils;

import com.github.javafaker.Faker;
import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.AccountService;
import nl.hava.dapaj.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class Factory {
    static final int NUMBER_OF_CITIES = 6;
    static final int NUMBER_OF_STREETS = 80;
    static final int NUMBER_OF_HOUSES = 100;
    static final int NUMBER_OF_IBANS = 2000;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService = new AccountService();

    private Faker faker;
    private List<Address> addresses;
    private List<String> iBans;


    public Factory(){
        this.faker = new Faker(new Locale("nl"));
        this.addresses = new ArrayList<>();
        this.iBans = new ArrayList<>();
        generateAddresses();
        generateIbans();
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
        LocalDate dateOfBirth = faker.date().birthday(18, 83).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String email = String.format(firstName + lastName + "@example.com");
        User user = new User(firstName, prefix, lastName, address, socialSecurityNumber, dateOfBirth, email);
        user.setPassword(faker.funnyName().name());
        return user;
    }

    private Customer generateCustomer(){
        User user = generateUser();
        Customer customer = new Customer(user.getFirstName(), user.getPrefix(), user.getLastName(), user.getAddress(),
                user.getSocialSecurityNumber(), user.getDateOfBirth(), user.getEmail());
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
        int randomIndex = (int) (Math.random() * size);
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

    public SMEAccount generateSMEAccount(Company company, List<Employee> bankEmployees){
        String iBan = pickRandom(this.iBans);
        String sector = faker.company().industry();
        SMEAccount smeAccount = new SMEAccount(iBan, sector, bankEmployees.get(1), company);
        return smeAccount;
    }


    public List<Employee> generateBankEmployees(){
        List<Employee> bankEmployees = new ArrayList<>();
        Customer employee1 = new Customer("Dirk", "van der", "Straaten", new Address()
                , "186016190", faker.date().birthday(18, 83).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "dirk@hallo.nl");
        Customer employee2 = new Customer("Dirk", "van der", "Straaten", new Address()
                , "186016190", faker.date().birthday(18, 83).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "dirk@hallo.nl");
        //customerList.addAll(customers);
        Employee managerRetail = new Employee("Manager Retail", employee1);
        Employee managerSME = new Employee("Manager SME", employee2);
        bankEmployees.add(managerRetail);
        bankEmployees.add(managerSME);
        return bankEmployees;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Transaction transactionfactory(List<Account> accounts){
        Account debitAccount = accounts.get((int)(Math.random()*accounts.size()));
        Account creditAccount = accounts.get((int)(Math.random()*accounts.size()));
       if (creditAccount.getAccountID() == debitAccount.getAccountID()){
            creditAccount = accounts.get((int)(Math.random()*accounts.size()));
        }
        double amount =  (Math.random()*10000)/100;
        String description = faker.funnyName().name();

       return new Transaction(debitAccount, creditAccount, amount, description);
    }

    public static int getNumberOfCities() {
        return NUMBER_OF_CITIES;
    }

    public static int getNumberOfStreets() {
        return NUMBER_OF_STREETS;
    }

    public static int getNumberOfHouses() {
        return NUMBER_OF_HOUSES;
    }

    public static int getNumberOfIbans() {
        return NUMBER_OF_IBANS;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<String> getiBans() {
        return iBans;
    }

    public void setiBans(List<String> iBans) {
        this.iBans = iBans;
    }
}
