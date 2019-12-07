package nl.hava.dapaj.bankapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends User {
    @ManyToMany
    private Set<Account> accounts;

    public Customer(){
        super();
    }

    public Customer(String firstName, String prefix, String lastName,
                       Address address) {
        super(firstName, prefix, lastName, address);
        super.setLoginName(this.createUserName());
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

}
