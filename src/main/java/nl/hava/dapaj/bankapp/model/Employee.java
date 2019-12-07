package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee extends User {
    //met onderstaande id werkt Hibernate niet
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID;*/

    private String employeeLoginName;
    private String employeePassword;
    private String role;

    @OneToMany(mappedBy = "accountManager")
    private Set<SMEAccount> managedAccounts;

    /*@OneToOne(mappedBy = "")
    private Customer customer;*/

    public Employee(){
        super();
    }

    public Employee(String role, Customer customer) {
        super(customer.getFirstName(), customer.getPrefix(), customer.getLastName(), customer.getAddress());
        this.employeeLoginName = this.createEmployeeLoginName();
        this.role = role;
        //this.customer = customer;
    }

    private String createEmployeeLoginName() {
        return String.format("Emp$"+this.loginName);
    }

    /*public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }*/

    public String getEmployeeLoginName() {
        return employeeLoginName;
    }

    public void setEmployeeLoginName(String employeeLoginName) {
        this.employeeLoginName = employeeLoginName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

    public Set<SMEAccount> getManagedAccounts() {
        return managedAccounts;
    }

    public void setManagedAccounts(Set<SMEAccount> managedAccounts) {
        this.managedAccounts = managedAccounts;
    }
}
