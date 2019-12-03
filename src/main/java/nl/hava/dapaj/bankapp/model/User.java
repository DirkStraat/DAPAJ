package nl.hava.dapaj.bankapp.model;

import java.util.Set;

public abstract class User {
    protected int customerId;
    protected String firstName;
    protected String prefix;
    protected String lastName;
    protected String loginName;
    protected String password;
    protected String address;
    protected String postcode;
    protected String city;
    protected String country;
    protected Set<Account> accounts;

    protected User(String firstName, String prefix, String lastName, String address, String postcode, String city, String country) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public abstract String creatUserName();


}
