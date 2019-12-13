package nl.hava.dapaj.bankapp.model;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int customerId;

    @NotNull
    @Length(min = 2)
    protected String firstName;
    protected String prefix;

    @NotNull
    @Length(min = 2)
    protected String lastName;

    @Length(min = 3) //for simplicity now to do checks, but it should be longer for security reasons
    protected String loginName;

    @Length(min = 3) //for simplicity now to do checks, but it should be at least 12 chars for security reasons
    protected String password;

    @NotNull
    @Size(min = 8)
    protected String socialSecurityNumber;
    protected Date dateOfBirth;

    @Email
    protected String email;

    @ManyToOne
    protected Address address;

    @ManyToMany
    protected Set<Company> companies;

    public User(){
        super();
    }

    public User(String firstName, String prefix, String lastName, Address address, String socialSecurityNumber,
                   Date dateOfBirth, String email) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.address = address;
        this.loginName = this.createUserName();
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;

    }

    protected String createUserName() {
        String part1 = this.firstName.substring(0,2);
        String part2 = this.lastName.substring(0,2);
        String part3 = Integer.toString((int)(Math.random()*300)+1);

        String userName = String.format(part1+part2+part3);

        return userName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public void addCompany(Company company){
        companies = new HashSet<>();
        this.companies.add(company);
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String bsn) {
        this.socialSecurityNumber = bsn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
