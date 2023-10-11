package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class representing a bank account.
 * @author Gleb Nickolaenko
 */
public class BankAccount {

    private String id;
    private BigDecimal balance;
    private List<Transaction> playerTransactions;

    /**
     * Constructs a new BankAccount with a unique ID, a balance of 0.00, and an empty list of transactions.
     */
    public BankAccount() {
        this.id = generateUniqueId();
        this.balance = new BigDecimal("0.00");
        this.playerTransactions = new ArrayList<>();
    }

    /**
     * Get the unique ID of the bank account.
     *
     * @return The unique ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the current balance of the bank account.
     *
     * @return The current balance.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Get the list of transactions associated with the bank account.
     *
     * @return The list of transactions.
     */
    public List<Transaction> getPlayerTransactions() {
        return playerTransactions;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Set the balance of the bank account.
     *
     * @param balance The new balance to set.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Set the unique ID of the bank account.
     *
     * @param id The new ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the list of transactions associated with the bank account.
     *
     * @param playerTransactions The list of transactions to set.
     */
    public void setPlayerTransactions(List<Transaction> playerTransactions) {
        this.playerTransactions = playerTransactions;
    }
}
