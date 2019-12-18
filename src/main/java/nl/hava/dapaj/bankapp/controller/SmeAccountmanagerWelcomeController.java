package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SmeAccountmanagerWelcomeController {

    IndexController indexController;

    @GetMapping("average_balance_branch")
    public String average_balance_branchHandler (Model model) { return "average_balance_branch"; }

    @GetMapping ("10_highest_balance_smec")
    public String highest_balace_smecHandler (Model model){
        return "10_highest_balance_smec"; }

    @GetMapping ("10_highest_volume_smec")
    public String highest_volume_smecHandler (Model model){
        return "10_highest_volume_smec"; }

    @GetMapping ("link_terminal")
    public String link_terminalHandler (Model model){
        return "link_terminal"; }
        
}
