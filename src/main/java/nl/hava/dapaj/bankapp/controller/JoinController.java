package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Address;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.UserDao;
import nl.hava.dapaj.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class JoinController {

    @Autowired
    UserService userService;

    @GetMapping("join_dapaj")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "join_dapaj";
    }

    @PostMapping("create_user")
    public String processJoinForm(@RequestParam(name ="last name") String lastName,
                                  @RequestParam(name ="name") String firstName,
                                  @RequestParam(name ="BSN") String BSN,
                                  @RequestParam(name = "birthday") Date dateOfBirth,
                                  @RequestParam(name = "address") Address address,
                                  @RequestParam(name = "email") String email
                                  ) {
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setBsn(BSN);
        user.setDateOfBirth(dateOfBirth);
        user.setAddress(address);
        user.setEmail(email);
        userService.save(user);
        return "redirect:/set_password";
    }

}
