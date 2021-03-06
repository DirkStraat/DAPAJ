
package nl.hava.dapaj.bankapp.controller;

        import nl.hava.dapaj.bankapp.model.User;
        import nl.hava.dapaj.bankapp.service.SMEAccountService;
        import nl.hava.dapaj.bankapp.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

@Controller
public class SetPasswordController {
    @Autowired
    private UserService userService;

/*    @Autowired
    private SMEAccountService smeAccountService;*/

    @PostMapping("change_password")
    public String changePasswordHandler(@RequestParam(name = "user_name") String name,
                                        @RequestParam(name = "user_password") String password,
                                        @RequestParam(name = "bsn") String bsn,
                                        Model model) {
        User user = userService.findUserByLoginName(name);
        if (user != null && bsn.equals(user.getSocialSecurityNumber())) {
            user.setPassword(password);
            userService.save(user);
            model.addAttribute("header_inlog", "Wachtwoord succesvol gewijzigd");
        } else if(user==null){
            User newUser = userService.findUserBySocialSecurityNumber(bsn);
            newUser.setLoginName(name);
            newUser.setPassword(password);
            userService.save(newUser);
            model.addAttribute("header_inlog", "Gebruikersnaam en wachtwoord opgeslagen");
        } else {
            model.addAttribute("header_inlog", "Wachtwoord kan niet gewijzigd worden!");
        }
        return "login";
    }
}


