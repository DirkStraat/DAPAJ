package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String companyName;

    @ManyToOne
    private Address address;

    @ManyToMany (mappedBy = "companies")
    private List<User> companyEmployees;

    @OneToMany(mappedBy = "company")
    private Set<SMEAccount> accounts;

    public Company() {
    }

    public Company(String companyName, Address address) {
        this.companyName = companyName;
        this.address = address;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public List<User> getCompanyEmployees() {
        return companyEmployees;
    }

    public void addCompanyEmployee(User user){
        this.companyEmployees.add(user);
    }

    public void setCompanyEmployees(List<User> companyEmployees) {
        this.companyEmployees = companyEmployees;
    }

    public Set<SMEAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<SMEAccount> accounts) {
        this.accounts = accounts;
    }
}
