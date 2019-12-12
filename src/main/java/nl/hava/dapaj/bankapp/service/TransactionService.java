package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Transaction;
import nl.hava.dapaj.bankapp.model.dao.AccountDao;
import nl.hava.dapaj.bankapp.model.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    AccountDao accountDao;

    public List<Transaction> getSortedListOfTransactionsByAccountId(Account account){
        List<Transaction> transactionsDebit = transactionDao.getTransactionsByDebitAccount(account);
        List<Transaction> transactionsCredit = transactionDao.getTransactionsByCreditAccount(account);

        List<Transaction> allTransactions = new ArrayList<>();
        for (Transaction transaction: transactionsCredit) {
            allTransactions.add(transaction);
        }

        for (Transaction transaction: transactionsDebit) {
            allTransactions.add(transaction);
        }

        Collections.sort(allTransactions);
        return allTransactions;
    }

    public void doTransAction(Account debitAccount, Account creditAccount, double amount, String description){
        double debitBalance = debitAccount.getBalance();
        double creditBalance = creditAccount.getBalance();

        if(debitBalance-amount> -1000){
            debitBalance -= amount;
            creditBalance += amount;
            debitAccount.setBalance(debitBalance);
            creditAccount.setBalance(creditBalance);
            accountDao.save(debitAccount);
            accountDao.save(creditAccount);
            transactionDao.save(new Transaction(debitAccount, creditAccount, amount, description));
        }
    }

}
