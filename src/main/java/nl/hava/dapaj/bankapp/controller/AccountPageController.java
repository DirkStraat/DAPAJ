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

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user", "account"})
public class AccountPageController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    LoginController loginController;

    @Autowired
    CompanyService companyService;

    @Autowired
    SMEAccountService smeAccountService;

    @GetMapping("add_representative")
    public String addRepresentativeHandler(Model model) {

        User user = (User)model.getAttribute("user");
        model.addAttribute("user", user);

        Account account = (Account)model.getAttribute("account");
        model.addAttribute("account", account);


        return "add_representative";
    }

    @GetMapping("transfer")
    public String transferHandler(Model model) {

        User user = (User)model.getAttribute("user");
        model.addAttribute("user", user);

        Account account = (Account)model.getAttribute("account");
        model.addAttribute("account", account);

        return "transfer";
    }

    public String accountPageHandler(int accountId, Model model){
        User user = (User)model.getAttribute("user");
        model.addAttribute("user", user);

        Account account = accountService.getAccountByAccountId(accountId);
        model.addAttribute("account", account);

        getRepresentatives(model, account);

        List<Transaction> transactions = transactionService.getSortedListOfTransactionsByAccountId(account);
        model.addAttribute("transactions", transactions);

        return "account_page";
    }

    private void getRepresentatives(Model model, Account account) {
        List<User> users = new ArrayList<>();
        List<Customer> customers = userService.findCustomersByAccountId(account);
        if(!customers.isEmpty()){
            for (Customer customer: customers) {
                users.add(customer);
            }
        }

        SMEAccount smeAccount = smeAccountService.getSMEAccountByAccountId(account.getAccountID());
        if (smeAccount!=null) {
            Company company = companyService.getCompanyByAccount(account);
            if (company != null) {
                for (User companyEmployee : company.getCompanyEmployees()) {
                    users.add(companyEmployee);
                }
            }
        }
        model.addAttribute("customers", users);
    }
}
