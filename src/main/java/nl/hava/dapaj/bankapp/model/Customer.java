package nl.hava.dapaj.bankapp.model;

public class Customer extends User {
    private static int customerCount = 10000;


    protected Customer(String firstName, String prefix, String lastName,
                       Address address) {
        super(firstName, prefix, lastName, address);
        this.customerId = customerCount;
        customerCount++;
        this.loginName = this.createUserName();
    }

    @Override
    public String toString() {
        if (prefix != null){
        return String.format(firstName +" "+prefix +" "+lastName +" "+ address+" "+loginName+" "+customerId);
        } else return String.format(firstName +" "+lastName +" "+ address+" "+loginName+" "+customerId);
    }
}
