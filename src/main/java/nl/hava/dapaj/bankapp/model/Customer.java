package nl.hava.dapaj.bankapp.model;

public class Customer extends User {
    private static int customerCount = 10000;


    protected Customer(String firstName, String prefix, String lastName,
                       String address, String postcode, String city, String country) {
        super(firstName, prefix, lastName, address, postcode, city, country);
        this.customerId = customerCount;
        customerCount++;
        this.loginName = this.creatUserName();

    }

    @Override
    public String creatUserName() {
        String part1 = this.firstName.substring(0,2);
        String part2 = this.lastName.substring(0,2);
        String part3 = Integer.toString(this.customerId%1000);

        String userName = String.format(part1+part2+part3);

        return userName;
    }

    @Override
    public String toString() {
        if (prefix != null){
        return String.format(firstName +" "+prefix +" "+lastName +" "+ address+" "+postcode+" "+city+" "+loginName+" "+customerId);
        } else return String.format(firstName +" "+lastName +" "+ address+" "+postcode+" "+city+" "+loginName+" "+customerId);
    }
}
