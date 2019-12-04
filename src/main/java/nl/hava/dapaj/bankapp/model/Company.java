package nl.hava.dapaj.bankapp.model;

import java.util.List;

public class Company {
    String companyName;
    Address address;
    List<Customer> companyEmployees;

    public Company(String companyName, Address address) {
        this.companyName = companyName;
        this.address = address;
    }
}
