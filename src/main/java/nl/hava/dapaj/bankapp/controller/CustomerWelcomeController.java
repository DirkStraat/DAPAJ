package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class CustomerWelcomeController {

    @PostMapping("do_select_account")
    public String doSelectAccountHandler(Model model) {
        return "account";
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        return "link_terminal.html";
    }


}
