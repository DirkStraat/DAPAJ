package nl.hava.dapaj.bankapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends User {
    @ManyToMany(mappedBy = "customers")
    private Set<Account> accounts;

    public Customer(){
        super();
    }

    public Customer(String firstName, String prefix, String lastName,
                    Address address, String socialSecurityNumber, Date dateOfBirth, String email) {
        super(firstName, prefix, lastName, address, socialSecurityNumber, dateOfBirth, email);
        accounts = new HashSet<>();
    }

    @Override
    public String toString() {
        if (super.getPrefix() != null){
        return String.format(super.getFirstName() +" "+super.getPrefix() +" "+super.getLastName() +" "+ super.getAddress()+
                " "+super.getLoginName()+" "+this.customerId);
        } else return String.format(super.getFirstName() +" "+super.getLastName() +" "+ super.getAddress()+
                " "+super.getLoginName()+" "+super.getCustomerId());
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

}
