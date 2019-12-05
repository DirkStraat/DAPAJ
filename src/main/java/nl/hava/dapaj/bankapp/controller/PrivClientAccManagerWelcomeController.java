package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PrivClientAccManagerWelcomeController {

    @GetMapping("10_highestbalance_pc")
    public String privClientAccManagerWelcomeHandler(Model model) { return "10_highestbalance_pc"; }
}
