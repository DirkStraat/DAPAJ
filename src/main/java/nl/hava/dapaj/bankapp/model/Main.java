package nl.hava.dapaj.bankapp.model;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Factory fabriekje = new Factory();
        Set<Customer> customers = fabriekje.generateCustomers(100);
        for (Customer customer: customers) {
            System.out.println(customer);
        }
    }
}
