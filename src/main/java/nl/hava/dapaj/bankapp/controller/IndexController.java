package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @PostMapping("go_to_login")
    public String go_to_loginHandler (Model model){
        return "login_template";
    }

}
