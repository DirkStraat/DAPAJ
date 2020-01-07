package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.*;
import nl.hava.dapaj.bankapp.utils.IBANGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

                                  //now the requestparam for the address
                                  @RequestParam(name = "street") String street,
                                  @RequestParam(name = "house_number") int housenumber,
                                  @RequestParam(name = "insert") String suffix,
                                  @RequestParam(name = "postcode") String postcode,
                                  @RequestParam(name = "city") String city,
                                  @RequestParam(name = "country") String country,

                                  //now check if the user wants to open an account, and what type of account
                                  @RequestParam(name = "account_type") String accountType,

                                  //company info
                                  @RequestParam(name = "company_name") String companyName,
                                  //@RequestParam(name = "company_sector") String companySector, //do we want this, or is branch another thing?
                                  @RequestParam(name = "company_street") String companyStreet,
                                  @RequestParam(name = "company_number") int companyNumber,
                                  @RequestParam(name = "company_postcode") String companyPostcode,
                                  @RequestParam(name = "company_city") String companyCity,
                                  @RequestParam(name = "company_country") String companyCountry
                                  ) {

        //create the user with the form info
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPrefix(prefix);
        user.setSocialSecurityNumber(BSN);
        user.setDateOfBirth(dateOfBirth);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
            //now make the user sub-object Address
            Address address = new Address();
            address.setStreet(street);
            address.setHousenumber(housenumber);
            address.setSuffix(suffix);
            address.setPostcode(postcode);
            address.setCity(city);
            address.setCountry(country);
            user.setAddress(address);
            addressService.save(address);
        //save the user in the database
        userService.save(user);

        //IF corporate account, create the company info
        Company company = new Company();
        company.setCompanyName(companyName);
            //company address
            Address companyAddress = new Address();
            companyAddress.setSuffix("Co.");
            companyAddress.setStreet(companyStreet);
            companyAddress.setHousenumber(companyNumber);
            companyAddress.setPostcode(companyPostcode);
            companyAddress.setCity(companyCity);
            companyAddress.setCountry(companyCountry);
            company.setAddress(companyAddress);
            addressService.save(companyAddress);
        companyService.saveCompany(company);

        //save user as an employee to the company
        List<User> companyEmployees = null;
        assert false;
        companyEmployees.add(user);
        company.setCompanyEmployees(companyEmployees);

        if (accountType.equals("Private")) { //create an Account
            String privateIban = IBANGenerator.generateIBAN();
            Account privateAccount = new Account(privateIban);
                privateAccount.setAccountName(firstName + lastName);
            accountService.save(privateAccount);

        } else if (accountType.equals("Corporate")) { //create an SMEAccount
            String corporateIban = IBANGenerator.generateIBAN();
            SMEAccount corporateAccount = new SMEAccount(corporateIban, "branch", new Employee(), company);
            smeAccountService.save(corporateAccount);
        }


        return "redirect:/set_password";
    }
}
