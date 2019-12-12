package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.Transaction;
import nl.hava.dapaj.bankapp.model.TransactionComparator;
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

        for (Transaction tr : allTransactions) {
            System.out.println(tr);
        }

        Collections.sort(allTransactions);
        return allTransactions;
    }

}
