package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
public class Transaction implements Comparable<Transaction> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @ManyToOne
    private Account debitAccount;

    @ManyToOne
    private Account creditAccount;

    @OneToOne
    private Account suspenseAccount;

    @NotNull //of NotEmpty?
    @DecimalMin(value = "0.01")
    private double amount;


    private String description;

    private LocalDate date;

    private LocalTime time;


    public Transaction(){
        super();
    }

    public Transaction(Account debitAccount, Account creditAccount, double amount, String description){
        super();
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public int compareTo(Transaction t){
        if (t.getDate().compareTo(this.getDate()) != 0){
            return t.getDate().compareTo(this.getDate());
        } else  return t.getTime().compareTo(this.getTime());
    }
}
