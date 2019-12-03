package nl.hava.dapaj.bankapp.model;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionID;
    private Account debitAccount;
    private Account creditAccount;
    private Account suspenseAccount;
    private double amount;
    private String description;
    private LocalDateTime dateTimeTransaction;
}
