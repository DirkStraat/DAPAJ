package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Address;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AddressService;
import nl.hava.dapaj.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class JoinController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

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
                                  @RequestParam(name = "country") String country

                                  //now check if the user wants to open an account
                                  //@RequestParam(name = "open_private_account") String privateAccount,
                                  //@RequestParam(name = "open_corporate_account") String corporateAccount

                                  ) {

/*        if (privateAccount) { //if visitor chooses to open a private account
            //then call Customer constructor
        } else if (corporateAccount) {
            //in this case call ...
        }*/

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
        return "redirect:/set_password";
    }
}
