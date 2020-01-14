package nl.hava.dapaj.bankapp.controller;


import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Transaction;
import nl.hava.dapaj.bankapp.service.AccountService;
import nl.hava.dapaj.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.validation.constraints.DecimalMin;



@Controller
@SessionAttributes ({"account","user"}) //het moet nog even gecontroleerd worden
@Validated
public class TransferController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountPageController accountPageController;

    @PostMapping("do_transfer")
    public String doTransferHandler(@RequestParam String yourCreditAccount,
                                    @RequestParam
                               //    @DecimalMin(value = "0.01", message = "input format onjuist")
                                            String amount,
                                    @RequestParam String description,
                                    Model model) {
        Account debitAccount = (Account) model.getAttribute("account");

        if ((accountExists(yourCreditAccount)) && (isDouble(amount)) && (Double.parseDouble(amount) >= 0.01)) {
            Account creditAccount = accountService.getAccountByIban(yourCreditAccount);
            Transaction transaction = new Transaction(debitAccount, creditAccount, Double.parseDouble(amount), description);
            transactionService.doTransAction(transaction);
            accountPageController.enterAccountPage(debitAccount.getAccountID(), model);
            model.addAttribute("motd", "Transactie succesvol");

            return "account_page";
        } else {
            model.addAttribute("motd", "iban of bedrag onjuist");
            accountPageController.enterAccountPage(debitAccount.getAccountID(), model);
            return "account_page";
        }
    }




    private boolean accountExists(String accountNummer) {
        for (Account number : accountService.findAllAccounts()) {
            if (number.getIban().equals(accountNummer)){
                return true;
            }
        }
        return false;
    }


    public boolean isDouble(String amount) {
        try {
            double d = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}


