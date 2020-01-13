package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes({"user", "account", "customer", "invitations"})
public class CustomerWelcomeController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthorizationInvitationService authorizationInvitationService;

    @Autowired
    private AccountPageController accountPageController;

    @GetMapping("do_select_account")
    public String doSelectAccountHandler(@RequestParam(name = "account_id") int id, Model model) {
        accountPageController.enterAccountPage(id, model);
        return "account_page";
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        User user = (User)model.getAttribute("user");
        List<AuthorizationInvitation> invitations = authorizationInvitationService.getInvitationsByUser(user);
        model.addAttribute("motd", "Kies hier de rekening die u wilt koppelen en voer de vijfcijferige koppelcode in.");
        model.addAttribute("invitations", invitations);

        return "link_account";
    }

    @GetMapping("customerWelcome") // te gebruiken voor redirect naar de customer_welcome pagina
    public String customerWelcomeHandler(Model model){
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
