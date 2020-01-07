package nl.hava.dapaj.bankapp.controller;

import net.bytebuddy.pool.TypePool;
import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Transaction;
import nl.hava.dapaj.bankapp.model.User;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Controller
@SessionAttributes ({"account","user"}) //het moet nog even gecontroleerd worden
@Validated
public class TransferController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;




    @PostMapping("do_transfer")
    public String doTransferHandler(@RequestParam  String yourCreditAccount,
                                    @RequestParam
                                    @DecimalMin(value="0.01", message="input format onjuist" ) String amount,
                                    @RequestParam String description,
                                    Model model) {

            Account creditAccount = accountService.getAccountByIban(yourCreditAccount);
            Account debitAccount = (Account) model.getAttribute("account");
            if (amount != null && !(amount.isEmpty())  && !(yourCreditAccount.isEmpty())){
                Transaction transaction = new Transaction(debitAccount, creditAccount, Double.parseDouble(amount), description);
                transactionService.doTransAction(transaction);
                model.addAttribute("transfer_message", "Transactie succesvol");
            } else {
                model.addAttribute("transfer_message", "ERROR/ Bedrag of IBAN leeg -probeer nogmaals");
            }
            return "account_page";
        }
    }



