package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int accountID;

    protected String accountName;

    @Column(unique = true)
    protected String iban;

    @ManyToMany (fetch = FetchType.EAGER)
    protected Set<Customer> customers;

    protected double balance;

    @OneToMany (fetch = FetchType.EAGER)
    protected List<AuthorizationInvitation> aRInvitations;

    public Account(){
        super();
    }

    public Account(String iban) {
        this.accountName = "this must be replaced";
        this.iban = iban;
        this.customers = new HashSet<>();
        this.balance = 0.0;
        this.aRInvitations = new ArrayList<>();

    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public List<AuthorizationInvitation> getaRInvitations() {
        return aRInvitations;
    }

    public void setaRInvitations(List<AuthorizationInvitation> aRInvitations) {
        this.aRInvitations = aRInvitations;
    }

    @Override
    public String toString() {
        return String.format(iban);
    }
}

