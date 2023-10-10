package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private String id;
    private BigDecimal balance;
    private List<Transaction> playerTransactions;

    public BankAccount() {
        this.id = generateUniqueId();
        this.balance = new BigDecimal("0.00");
        this.playerTransactions = new ArrayList<>();
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

    private String generateUniqueId() {
        // Генерируем уникальный ID с использованием UUID (Universally Unique Identifier)
        return UUID.randomUUID().toString();
    }
}
