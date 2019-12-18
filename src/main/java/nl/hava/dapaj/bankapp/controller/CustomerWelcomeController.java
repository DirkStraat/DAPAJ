package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.AccountService;
import nl.hava.dapaj.bankapp.service.AuthorizationInvitationService;
import nl.hava.dapaj.bankapp.service.TransactionService;
import nl.hava.dapaj.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"user", "account", "customer", "linkAccount"})
public class CustomerWelcomeController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthorizationInvitationService authorizationInvitationService;



    @GetMapping("do_select_account")
    public String doSelectAccountHandler(@RequestParam(name = "account_id") int id, Model model) {

        User user = (User)model.getAttribute("user");
        model.addAttribute("user", user);

        Account account = accountService.getAccountByAccountId(id);
        model.addAttribute("account", account);

        List<Customer> userList = userService.findCustomersByAccountId(account);
        model.addAttribute("customers", userList);

        List<Transaction> transactions = transactionService.getSortedListOfTransactionsByAccountId(account);
        model.addAttribute("transactions", transactions);

        return "account_page";
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        User user = (User)model.getAttribute("user");
        List<AuthorizationInvitation> invitations = authorizationInvitationService.getInvitationsByUser(user);
        Account linkAccount = invitations.get(0).getAccount();
        model.addAttribute("linkAccount", linkAccount);

        return "link_account";
    }


}
