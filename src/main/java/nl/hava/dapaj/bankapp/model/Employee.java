package nl.hava.dapaj.bankapp.model;

public class Employee extends User {
    private int employeeID;
    private String employeeLoginName;
    private String employeePassword;
    private String role;

    public Employee(String firstName, String prefix, String lastName, Address address, String role) {
        super(firstName, prefix, lastName, address);
        this.employeeID = employeeID;
        this.employeeLoginName = this.createEmployeeLoginName();
        this.role = role;
    }

    public String createEmployeeLoginName() {
        return String.format("Emp$"+this.loginName);
    }
}
