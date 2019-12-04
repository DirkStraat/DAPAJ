package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerWelcomeController {

    @PostMapping("do_select_account")
    public String doSelectAccountHandler(Model model) {
        return "";
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        return "";
    }
}
