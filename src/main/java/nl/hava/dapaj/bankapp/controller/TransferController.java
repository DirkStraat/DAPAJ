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
import javax.validation.constraints.Max;
import java.lang.reflect.Array;
import java.util.List;


@Controller
@SessionAttributes ({"account","user"}) //het moet nog even gecontroleerd worden
@Validated
public class TransferController {
    final static double CREDIT_LIMIT = -1000.0;

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
        Account creditAccount = accountService.getAccountByIban(yourCreditAccount);
        String amountDouble = checkForComma(amount);

        if(!(accountExists(yourCreditAccount))) {
            model.addAttribute("motd", "iban onjuist");
            return accountPageController.accountPageHandler(debitAccount.getAccountID(), model);
        }
            else if (!(isDouble(amountDouble))) {
                model.addAttribute("motd", "bedrag onjuist");
            return accountPageController.accountPageHandler(debitAccount.getAccountID(), model);

        }
            else if (Double.parseDouble(amountDouble) < 0.01) {
            model.addAttribute("motd", "bedrag te klein");
            return accountPageController.accountPageHandler(debitAccount.getAccountID(), model);
        }
            else if(debitAccount.getBalance() - Double.parseDouble(amountDouble) < CREDIT_LIMIT) {
            model.addAttribute("motd", "saldo niet toereikend");
            return accountPageController.accountPageHandler(debitAccount.getAccountID(), model);
        }
            else {
                Transaction transaction = new Transaction(debitAccount, creditAccount, Double.parseDouble(amountDouble), description);
                transactionService.doTransAction(transaction);
            return accountPageController.accountPageHandler(debitAccount.getAccountID(), model);
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


    private String checkForComma(String amount){
        System.out.println(amount);
        amount = amount.replace(',','.');
        System.out.println(amount);
        return amount;
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


