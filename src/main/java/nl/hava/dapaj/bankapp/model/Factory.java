package nl.hava.dapaj.bankapp.model;

import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Factory {
    public static final int POSTCODE_MINIMUM = 50;
    public static final int POSTCODE_SCHAAL = 5;

    private Faker faker;

    public Factory(){
        this.faker = new Faker(new Locale("nl"));
    }


    private String createRandomPostcode() {
        String result;
        int postcodeGetal = (int) (POSTCODE_MINIMUM + Math.random() * POSTCODE_SCHAAL);
        char letter1 = (char)('A' + Math.random() * ('D' - 'A' + 1));
        char letter2 = (char)('A' + Math.random() * ('D' - 'A' + 1));
        result = "10" + postcodeGetal + letter1 + letter2;
        return result;
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
        Address address = generateAddress();
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

    public Address generateAddress(){
        String street = faker.address().streetName();
        String houseNr = Integer.toString((int)(Math.random()*600)+1);
        String addressString = String.format(street + " " + houseNr);
        String postcode = this.createRandomPostcode();
        String city = faker.address().city();

        Address address = new Address(addressString, postcode, city, "Nederland");
        return address;
    }

    public Company generateCompany(){
        String companyName = faker.company().name();
        Address address = generateAddress();
        Company company = new Company(companyName, address);
        return company;
    }
}
