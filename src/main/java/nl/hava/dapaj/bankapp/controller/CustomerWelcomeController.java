package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class CustomerWelcomeController {
    @GetMapping("do_select_account")
    public String doSelectAccountHandler(Model model) {
        User user = (User)model.getAttribute("user");
        return "test";
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        return "link_terminal.html";
    }


}
