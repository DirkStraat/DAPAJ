package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @PostMapping("go_to_login")
    public String go_to_loginHandler (Model model){
        model.addAttribute("header_inlog", "Vul hier uw gegevens in");
        return "login";
    }

    @GetMapping("go_to_active_client_overview")
    public String goToActiveClientOverview (){
        return "active_client_overview";
    }

    @GetMapping("go_to_average_balance_branch")
    public String goTOAverageBalanceBranch (){
        return "average_balance_branch";
    }

    @GetMapping("go_to_customer_welcome")
    public String goToCustomerWelcome (){
        return "customer_welcome";
    }

    @GetMapping("go_to_link_terminal")
    public String goToLinkTerminal (){
        return "link_terminal";
    }

    @GetMapping("go_to_private_client_accountmanager_welcome")
    public String goToPrivateClientAccountmanagerWelcome (){
        return "private_client_accountmanager_welcome";
    }

    @GetMapping("go_to_sme_accountmanager_welcome")
    public String goToSmeAccountmanagerWelcome (){
        return "sme_accountmanager_welcome";
    }

    @GetMapping("go_to_transfer")
    public String goToTransfer (){
        return "transfer";
    }


}
