package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes("user")
@Controller
public class ActiveClientOverviewController {

    @Autowired
    private AccountService accountService;

    @GetMapping("customerWelcome")
    public String activeClientOverviewHandler(Model model){
        User user = (User)model.getAttribute("user");
        System.out.println("user 1"+ user.getLoginName());
        System.out.println("user 1"+ user.getFirstName());
        List<Account> accountList = accountService.getAccountByUser(user);      // verkrijgt user rekeningen
        List<Account> accountList1 = accountService.getAccountByCompany(user);  // verkrijgt company rekeningen
        for(Account account: accountList1){                                     // voegt de lijsten samen
            accountList.add(account);
        }
        System.out.println("user 2"+ user);
        model.addAttribute("user", user);
        System.out.println("user 3"+ user);
        model.addAttribute("accounts", accountList);
        return "customer_welcome";
    }

}
