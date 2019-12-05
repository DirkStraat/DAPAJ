package nl.hava.dapaj.bankapp.model;

public class Main {
    public static void main(String[] args) {
        Factory fabriekje = new Factory();
        fabriekje.generateCustomers(100);
    }
}
