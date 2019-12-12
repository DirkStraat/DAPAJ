package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"user", "account"})
public class CustomerWelcomeController {

    @Autowired
    private AccountService accountService;


    @GetMapping("do_select_account")
    public String doSelectAccountHandler(@RequestParam(name = "account_id") int id,
            Model model) {
        User user = (User)model.getAttribute("user");
        Account account = accountService.getAccountByAccountId(id);
        model.addAttribute("account", account);
        assert user != null;
        System.out.println(user.getFirstName());
        System.out.println(account.getIban());
        return "account_page";
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        return "link_terminal.html";
    }


}
