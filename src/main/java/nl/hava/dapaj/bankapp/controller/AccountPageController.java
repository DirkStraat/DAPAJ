package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("user")
public class AccountPageController {

    @Autowired
    private AccountService accountService;

    @PostMapping("add_representative")
    public String addRepresentativeHandler(Model model) {
        return "add_representative";
    }

    @PostMapping("transfer")
    public String transferHandler(Model model) {
        return "transfer";
    }

    @PostMapping("customer_welcome")
    public String customerWelcomeHandler(Model model){
        User user = (User)model.getAttribute("user");
        List<Account> accountList = accountService.getAccountByUserId(user.getCustomerId());
        model.addAttribute("accounts", accountList );
        return "customer_welcome";
    }
}
