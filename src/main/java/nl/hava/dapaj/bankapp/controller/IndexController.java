package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("login")
    public String loginHandler (Model model){
    return "login_template";
    }
}
