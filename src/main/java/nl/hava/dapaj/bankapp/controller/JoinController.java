package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.*;
import nl.hava.dapaj.bankapp.utils.IBANGeneratoRand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JoinController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    AddressService addressService;

    @Autowired
    AccountService accountService;

    @Autowired
    SMEAccountService smeAccountService;

    @Autowired
    EmpoyeeService employeeService;

    @GetMapping("join_dapaj")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "join_dapaj";
    }

    @PostMapping("create_user")
    public String processJoinForm(@RequestParam(name ="last name") String lastName,
                                  @RequestParam(name ="name") String firstName,
                                  @RequestParam(name ="insert") String prefix,
                                  @RequestParam(name ="BSN") String BSN,
                                  @RequestParam(name = "birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
                                  @RequestParam(name = "email") String email,
                                  @RequestParam(name = "street") String street,
                                  @RequestParam(name = "house_number") int houseNumber,
                                  @RequestParam(name = "insert") String suffix,
                                  @RequestParam(name = "postcode") String postcode,
                                  @RequestParam(name = "city") String city,
                                  @RequestParam(name = "country") String country,
                                  @RequestParam(name = "account_type") String accountType,

                                  //company info
                                  @RequestParam(name = "company_name") String companyName,
                                  @RequestParam(name = "company_sector") String companySector,
                                  @RequestParam(name = "company_street") String companyStreet,
                                  @RequestParam(name = "company_number") String companyNumber,
                                  @RequestParam(name = "company_postcode") String companyPostcode,
                                  @RequestParam(name = "company_city") String companyCity,
                                  @RequestParam(name = "company_country") String companyCountry,
                                  Model model
                                  ) {

        //create the Customer ...
        Address address = null;
        Customer user   = new Customer(firstName, prefix, lastName, address, BSN, dateOfBirth, email);

        //... check for existing Address otherwise create a new one
        address = addressService.getAddressByStreetandNumber(street, houseNumber);
        if(address == null) {
            address = new Address(street, houseNumber, postcode, city, country);
        }
        user.setAddress(address);
        addressService.save(address);
        userService.save(user);


        if (accountType.equals("private")) { //create an Account (particular account)
            String privateIban = IBANGeneratoRand.generateIBAN();
            Account privateAccount = new Account(privateIban);
            privateAccount.setAccountName(firstName + lastName);
            accountService.save(privateAccount);
            privateAccount.getCustomers().add(user); //an account has a set of users
            user.getAccounts().add(privateAccount); //user as a set of accounts
            accountService.save(privateAccount);
            userService.save(user);
        }
        else if (accountType.equals("corporate")) { //create an SMEAccount
            Address companyAddress = null;
            Company company = new Company(companyName, companyAddress);

            //company address
            int i = Integer.parseInt(companyNumber); //necessary parsing for the corporate number in form
            companyAddress = addressService.getAddressByStreetandNumber(companyStreet, i);
            if(companyAddress == null){
                companyAddress = new Address(companyStreet, i, companyPostcode, companyCity, companyCountry);
                companyAddress.setSuffix("Co.");
            }
            company.setAddress(companyAddress);
            addressService.save(companyAddress);

            //save user as an employee to the company
            List<User> companyEmployees = new ArrayList<>();
            companyEmployees.add(user);
            company.setCompanyEmployees(companyEmployees);
            //save company as a company of user
            user.getCompanies().add(company);
            companyService.saveCompany(company);
            userService.save(user); //user info is updated

            String corporateIban = IBANGeneratoRand.generateIBAN();
            Employee managerSME = employeeService.findEmployeeByRole("Manager SME");
            SMEAccount corporateAccount = new SMEAccount(corporateIban, companySector, managerSME, company);
            smeAccountService.save(corporateAccount);


        }

        model.addAttribute("new_user", true);

        return "set_password";
    }
}
