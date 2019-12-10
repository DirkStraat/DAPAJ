
package nl.hava.dapaj.bankapp.controller;

        import nl.hava.dapaj.bankapp.model.Customer;
        import nl.hava.dapaj.bankapp.model.User;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

@Controller
public class SetPasswordController {
    @PostMapping("change_password")
    public String changePasswordHandler(@RequestParam(name = "user_name") String userName,
                                        @RequestParam(name = "user_password") String userPassword, Model model) {
        return "login";
    }
}
