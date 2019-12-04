package nl.hava.dapaj.bankapp.model;

import java.util.Set;

public abstract class User {
    protected int customerId;
    protected String firstName;
    protected String prefix;
    protected String lastName;
    protected String loginName;
    protected String password;
    protected Address address;
    protected Set<Account> accounts;

    protected User(String firstName, String prefix, String lastName, Address address) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.address = address;
    }

    protected String createUserName() {
        String part1 = this.firstName.substring(0,2);
        String part2 = this.lastName.substring(0,2);
        String part3 = Integer.toString(this.customerId%1000);

        String userName = String.format(part1+part2+part3);

        return userName;
    }

}
