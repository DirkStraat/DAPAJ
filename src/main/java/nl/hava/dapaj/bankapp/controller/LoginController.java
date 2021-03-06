package nl.hava.dapaj.bankapp.controller;

import com.sun.javafx.binding.StringFormatter;
import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.model.dao.*;
import nl.hava.dapaj.bankapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SessionAttributes({"user"})
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmpoyeeService empoyeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private SmeAccountmanagerWelcomeController smeAccountmanagerWelcomeController;
    @Autowired
    private AuthorizationInvitationService authorizationInvitationService;

    @PostMapping ("join_dapaj")
    public String join_dapajHandler(Model model){
        return "join_dapaj";
    }
    @GetMapping ("set_password")
    public String set_passwordHandler (Model model){
        return "set_password";
    }

    @PostMapping("do_login")
    public String doLoginHandler(@RequestParam(name = "user_name") String loginName,
                                 @RequestParam(name = "user_password") String userPassword,
                                 Model model) {
        if (loginService.validatePassword(loginName, userPassword)) {
            enterCustomerWelcome(loginName, model);
            return "customer_welcome";
        }else if(loginService.validateEmployeePassword(loginName, userPassword)){
            User user = empoyeeService.findUserByEmployeeLoginName(loginName);
            if (((Employee) user).getRole().equals("Manager SME")){
                smeAccountmanagerWelcomeController.enterSmeAccountManagerWelcome(model, user);
                return "sme_accountmanager_welcome";
            }else if(((Employee) user).getRole().equals("Manager Retail")){
                return "private_client_accountmanager_welcome";
            }else{
                enterLogin(model);
                return "login";
            }
        }else {
            enterLogin(model);
            return "login";
        }
    }

    private void enterLogin(Model model) {
        model.addAttribute("header_inlog", "Naam/password combinatie niet bekend");
    }


    public void enterCustomerWelcome(@RequestParam(name = "user_name") String loginName, Model model) {
        User user = userService.findUserByLoginName(loginName);
        List<AuthorizationInvitation> invitations = authorizationInvitationService.getInvitationsByUser(user);
        List<Account> accountList = accountService.getAccountByUser(user);      // verkrijgt user rekeningen
        List<Account> accountList1 = accountService.getAccountByCompany(user);  // verkrijgt company rekeningen
        for(Account account: accountList1){                                     // voegt de lijsten samen
            accountList.add(account);
        }
        model.addAttribute("user", user);
        if (model.getAttribute("motd") == null){
            String motd = String.format("Welkom %s.", user.getFirstName());
            model.addAttribute("motd", motd);
        }
        model.addAttribute("accounts", accountList);
        model.addAttribute("invitations", invitations);
    }

    @GetMapping("logout")
    public String logoutHandler(){
        return "login";
    }
}
