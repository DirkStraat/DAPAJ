package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.AuthorizationInvitation;
import nl.hava.dapaj.bankapp.model.Customer;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
import nl.hava.dapaj.bankapp.service.AuthorizationInvitationService;
import nl.hava.dapaj.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import static nl.hava.dapaj.bankapp.utils.IBANGeneratoRand.generateIBAN;

@Controller
@SessionAttributes({"user", "account", "customer", "invitations"})
public class CustomerWelcomeController {

    @Autowired
    LoginController loginController;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationInvitationService authorizationInvitationService;

    @Autowired
    private AccountPageController accountPageController;

    @GetMapping("do_select_account")
    public String doSelectAccountHandler(@RequestParam(name = "account_id") int id, Model model) {
        return accountPageController.accountPageHandler(id, model);
    }

    @PostMapping("do_link_account")
    public String doLinkAccountHandler(Model model) {
        User user = (User) model.getAttribute("user");
        List<AuthorizationInvitation> invitations = authorizationInvitationService.getInvitationsByUser(user);
        model.addAttribute("motd", "Kies hier de rekening die u wilt " +
                "koppelen en voer de vijfcijferige koppelcode in.");
        model.addAttribute("invitations", invitations);
        return "link_account";
    }

    @PostMapping("do_open_new_account")
    public String doOpenNewAccount(Model model) {
        User user = (User) model.getAttribute("user");
        Account account = new Account(generateIBAN());
        if (user != null) {
            account.setAccountName(user.getFirstName() + user.getLastName());
            account.getCustomers().add((Customer) user);
            ((Customer) user).getAccounts().add(account);
            accountService.save(account);
            userService.save(user);
            fillModel(model);
        }
        return "customer_welcome";
    }

    @GetMapping("customer_welcome")
    public String customerWelcomeHandler(Model model) {
       User user = (User) model.getAttribute("user");
        loginController.enterCustomerWelcome(user.getLoginName(), model);
        return "customer_welcome";
    }

    public void fillModel(Model model){
        System.out.println("model 1" + model);
        User user = (User) model.getAttribute("user");
        System.out.println("model wordt gevuld");
        System.out.println("model 2" + model);
        if (user != null) {
            List<AuthorizationInvitation> invitations = authorizationInvitationService.getInvitationsByUser(user);
            List<Account> accountsByUser = accountService.getAccountByUser(user);
            List<Account> accountsByCompany = accountService.getAccountByCompany(user);
            accountsByUser.addAll(accountsByCompany);
            for (Account account : accountsByUser
            ) {
                System.out.println(account.getBalance());
            }
            model.addAttribute("user", user);
            model.addAttribute("accounts", accountsByUser);
            model.addAttribute("invitations", invitations);
            if (model.getAttribute("motd") == null) {
                String motd = String.format("Welkom %s.", user.getFirstName());
                model.addAttribute("motd", motd);
            }
            System.out.println("model 3" + model);
        }
    }

}
