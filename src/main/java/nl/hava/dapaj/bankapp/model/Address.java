package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adressId;

    private String street;
    private int housenumber;
    private String suffix;
    private String postcode;
    private String city;
    private String country;

    @OneToMany(mappedBy = "address")
    private Set<User> inhabitants;

    @OneToMany(mappedBy = "address")
    private Set<Company> companies;

    public Address() {
        super();
    }

    public Address (String street, int housenumber, String postcode, String city, String country) {
        this.street = street;
        this.housenumber = housenumber;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public Address(String street, int housenumber, String suffix, String postcode, String city, String country) {
        this(street, housenumber, postcode, city, country);
        this.suffix = suffix;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(int housenumber) {
        this.housenumber = housenumber;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Set<User> getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(Set<User> inhabitants) {
        this.inhabitants = inhabitants;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        if (suffix == null) {
            return String.format(street + " " + housenumber + " " + postcode + " " + city + " " + country);
        } else  return String.format(street + " " + housenumber + " " +suffix+ " "+ postcode + " " + city + " " + country);
     }
}
