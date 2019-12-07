package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int accountID;

    protected String accountName;
    protected String iban;

    @ManyToMany(mappedBy="accounts")
    protected Set<User> authorizedRepresentatives;

    protected double balance;

    public Account(){
        super();
    }

    public Account(String iban, Customer customer) {
        this.accountName = String.format(customer.firstName.charAt(0) + customer.prefix + customer.lastName);
        this.iban = iban;
        this.authorizedRepresentatives = new HashSet<>();
        authorizedRepresentatives.add(customer);
        this.balance = 0.0;
    }

    public Account (String iban){
        this.iban = iban;
        this.balance = 0.0;
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

    public Set<User> getAuthorizedRepresentatives() {
        return authorizedRepresentatives;
    }

    public void setAuthorizedRepresentatives(Set<User> authorizedRepresentatives) {
        this.authorizedRepresentatives = authorizedRepresentatives;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
