package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @OneToOne
    private Account debitAccount;

    @OneToOne
    private Account creditAccount;

    @OneToOne
    private Account suspenseAccount;

    private double amount;
    private String description;
    private LocalDateTime dateTimeTransaction;

    public Transaction(){
        super();
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Account getSuspenseAccount() {
        return suspenseAccount;
    }

    public void setSuspenseAccount(Account suspenseAccount) {
        this.suspenseAccount = suspenseAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public void setDateTimeTransaction(LocalDateTime dateTimeTransaction) {
        this.dateTimeTransaction = dateTimeTransaction;
    }
}
