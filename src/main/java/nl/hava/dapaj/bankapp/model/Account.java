package nl.hava.dapaj.bankapp.model;

import java.util.Set;

public abstract class Account {
    private int accountID;
    private String accountName;
    private String iban;
    private Set<User> authorizedRepresentatives;
    private double balance;



}
