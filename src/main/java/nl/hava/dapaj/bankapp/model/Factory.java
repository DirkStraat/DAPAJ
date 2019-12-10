package nl.hava.dapaj.bankapp.model;

import com.github.javafaker.Faker;
import nl.hava.dapaj.bankapp.utils.IBANGenerator;

import java.util.*;


public class Factory {
    static final int NUMBER_OF_CITIES = 10;
    static final int NUMBER_OF_STREETS = 20;
    static final int NUMBER_OF_HOUSES = 50;
    static final int NUMBER_OF_IBANS = 10000;

    private Faker faker;
    private List<Address> addresses;
    private List<String> iBans;
    private List<Employee> bankEmployees;


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

    private Customer generateCustomer(){
        String firstName = faker.name().firstName();
        String prefix = this.createPrefix();
        String lastName = faker.name().lastName();
        Address address = pickRandom(addresses);
        Customer customer = new Customer(firstName, prefix, lastName, address);
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

    public void generateAddresses(){
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

    private Account generateRetailAccount(Customer customer){
        String iBan = pickRandom(this.iBans);
        return new Account(iBan, customer);
    }

    private SMEAccount generateSMEAccount(){
        String iBan = pickRandom(this.iBans);
        String sector = faker.company().industry();
        Company company = this.generateCompany();
        SMEAccount smeAccount = new SMEAccount(iBan, sector, bankEmployees.get(1), company, new Customer());
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



}
