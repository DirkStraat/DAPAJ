package nl.hava.dapaj.bankapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class SMEAccount extends Account {
    private String branch;

    @ManyToOne
    private Employee accountManager;
    private boolean payTerminal;

    @ManyToOne
    private Company company;

    public SMEAccount() {
        super();
    }

    public SMEAccount(String iban, String branch, Employee accountManager, Company company, Customer customer) {
        super(iban);
        this.branch = branch;
        this.accountManager = accountManager;
        this.company = company;
        super.accountName = String.format(company.getCompanyName() + company.getAddress().getCity());

    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Employee getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Employee accountManager) {
        this.accountManager = accountManager;
    }

    public boolean isPayTerminal() {
        return payTerminal;
    }

    public void setPayTerminal(boolean payTerminal) {
        this.payTerminal = payTerminal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
