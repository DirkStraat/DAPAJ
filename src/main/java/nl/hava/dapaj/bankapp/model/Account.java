package nl.hava.dapaj.bankapp.model;

import java.util.HashSet;
import java.util.Set;

public  class Account {
    private static int accountCount = 10000;
    private int accountID;
    protected String accountName;
    private String iban;
    protected Set<User> authorizedRepresentatives;
    private double balance;

    public Account(String iban, Customer customer) {
        this.accountID = accountCount;
        accountCount++;
        this.accountName = String.format(customer.firstName.charAt(0) + customer.prefix + customer.lastName);
        this.iban = iban;
        this.authorizedRepresentatives = new HashSet<>();
        authorizedRepresentatives.add(customer);
        this.balance = 0.0;
    }

    protected Account (String iban){
        this.accountID = accountCount + 10000;
        accountCount++;
        this.iban = iban;
        this.balance = 0.0;
    }
}
