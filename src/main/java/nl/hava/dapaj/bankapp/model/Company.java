package nl.hava.dapaj.bankapp.model;

import java.util.List;

public class Company {
    private String companyName;
    private Address address;
    private List<Customer> companyEmployees;

    public Company(String companyName, Address address) {
        this.companyName = companyName;
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Customer> getCompanyEmployees() {
        return companyEmployees;
    }

    public void setCompanyEmployees(List<Customer> companyEmployees) {
        this.companyEmployees = companyEmployees;
    }
}
