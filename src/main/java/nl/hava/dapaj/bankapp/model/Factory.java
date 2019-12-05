package nl.hava.dapaj.bankapp.model;

import com.github.javafaker.Faker;

import java.util.*;


public class Factory {
    static final int NUMBER_OF_CITIES = 10;
    static final int NUMBER_OF_STREETS = 20;
    static final int NUMBER_OF_HOUSES = 50;

    private Faker faker;
    private List<Address> addresses;

    public Factory(){
        this.faker = new Faker(new Locale("nl"));
        this.addresses = new ArrayList<>();
        generateAddresses();
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
        Address address = pickRandomAddress();
        Customer customer = new Customer(firstName, prefix, lastName, address);
        return customer;
    }

    private Employee generateEmployee(String role){
        Customer employeeAsCustomer = generateCustomer();
        Employee employee = new Employee(employeeAsCustomer.firstName, employeeAsCustomer.prefix,
                employeeAsCustomer.lastName, employeeAsCustomer.address, role);
        return employee;
    }

    public Set<Customer> generateCustomers(int amount){
        Set<Customer>  customers = new HashSet<>();

        for (int i = 0; i <amount ; i++) {
            Customer customer = this.generateCustomer();
            customers.add(customer);
        }

        for (Customer customer : customers) {
            System.out.println(customer);
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
                    String housenumber = String.valueOf(k+1);
                    String addressString = String.format(street + " " + housenumber);
                    Address address = new Address(addressString, postcode, city, country);
                    addresses.add(address);
                }
            }
        }
    }

    public Company generateCompany(){
        String companyName = faker.company().name();
        Address address = pickRandomAddress();
        Company company = new Company(companyName, address);
        return company;
    }

    private Address pickRandomAddress(){
        int addressessSize = addresses.size();
        int randomAddressIndex = (int)(Math.random()*addressessSize);
        Address address = addresses.get(randomAddressIndex);
        addresses.remove(randomAddressIndex);
        return address;
    }
}
