package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping ("join_dapaj")
    public String join_dapajHandler(Model model){
        return "join_dapaj";
    }
    @GetMapping ("set_password")
    public String set_passwordHandler (Model model){
        return "set_password";
    }

}
