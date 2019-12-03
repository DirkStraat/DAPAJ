package nl.hava.dapaj.bankapp.model;

import java.util.Set;

public abstract class User {
    private int customerId;
    private String firstName;
    private String prefix;
    private String lastName;
    private String loginName;
    private String password;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private Set<Account> accounts;


}
