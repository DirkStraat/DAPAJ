package nl.hava.dapaj.bankapp.model;

import java.util.HashSet;

public class SMEAccount extends Account {
    private String branch;
    private Employee accountManager;
    private boolean payTerminal;
    private Company company;

    public SMEAccount(String iban, String branch, Employee accountManager, Company company, Customer customer) {
        super(iban);
        this.branch = branch;
        this.accountManager = accountManager;
        this.company = company;
        super.accountName = String.format(company.companyName + company.address.getCity());
        this.authorizedRepresentatives = new HashSet<>();
        authorizedRepresentatives.add(customer);
    }
}
