package nl.hava.dapaj.bankapp.model;

public class Employee extends User {
    private int employeeID;
    private String employeeLoginName;
    private String employeePassword;
    private String role;

    public Employee(String firstName, String prefix, String lastName, String address, String postcode, String city, String country, int employeeID, String employeeLoginName, String employeePassword, String role) {
        super(firstName, prefix, lastName, address, postcode, city, country);
        this.employeeID = employeeID;
        this.employeeLoginName = employeeLoginName;
        this.employeePassword = employeePassword;
        this.role = role;
    }

    @Override
    public String creatUserName() {
        return null;
    }
}
