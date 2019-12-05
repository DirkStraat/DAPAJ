package nl.hava.dapaj.bankapp.model;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private String address;
    private String postcode;
    private String city;
    private String country;

    public Address(String address, String postcode, String city, String country) {
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return String.format(address+" "+postcode+" "+city+" "+country);
    }
}
