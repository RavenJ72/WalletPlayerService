package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String id;
    private BigDecimal balance;
    private List<Transaction> playerTransactions;

    public BankAccount(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
        this.playerTransactions =  new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getPlayerTransactions() {
        return playerTransactions;
    }
}
