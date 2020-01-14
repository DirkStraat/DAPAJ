package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Customer;
import nl.hava.dapaj.bankapp.model.Transaction;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
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
@SessionAttributes({"user", "account"})
public class AccountPageController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

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

    @GetMapping("customer_welcome")
    public String customerWelcomeHandler(Model model){

        User user = (User)model.getAttribute("user");

        List<Account> accountList = accountService.getAccountByUser(user);
        List<Account> accountList1 = accountService.getAccountByCompany(user);  // verkrijgt company rekeningen
        for(Account account: accountList1){                                     // voegt de lijsten samen
            accountList.add(account);
        }
        model.addAttribute("accounts", accountList );

        return "customer_welcome";
    }

    public void enterAccountPage(int accountId, Model model){
        User user = (User)model.getAttribute("user");
        model.addAttribute("user", user);

        Account account = accountService.getAccountByAccountId(accountId);
        model.addAttribute("account", account);

        List<Customer> userList = userService.findCustomersByAccountId(account);
        model.addAttribute("customers", userList);

        List<Transaction> transactions = transactionService.getSortedListOfTransactionsByAccountId(account);
        model.addAttribute("transactions", transactions);
    }
}
