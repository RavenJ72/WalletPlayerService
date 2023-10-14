package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class representing a financial transaction.
 *
 * @author Gleb Nickolaenko
 */
public class Transaction {
    private String id;
    private String type;
    private LocalDateTime creationTime;
    private BigDecimal amount;
    private String bankAccountId;

    /**
     * Constructs a new Transaction with the specified type, amount, and bank account ID.
     *
     * @param type        The type of the transaction (DEBIT or CREDIT).
     * @param amount      The amount of the transaction.
     * @param bankAccountId The unique ID of the bank account associated with the transaction.
     */
    public Transaction(String type, BigDecimal amount, String bankAccountId) {
        this.id = generateUniqueId();
        this.type = type;
        this.creationTime = LocalDateTime.now();
        this.amount = amount;
        this.bankAccountId = bankAccountId;
    }

    /**
     * Get the unique ID of the transaction.
     *
     * @return The transaction ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the type of the transaction (DEBIT or CREDIT).
     *
     * @return The transaction type.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the creation time of the transaction.
     *
     * @return The creation time.
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Get the amount of the transaction.
     *
     * @return The transaction amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Get the unique ID of the bank account associated with the transaction.
     *
     * @return The bank account ID.
     */
    public String getBankAccountId() {
        return bankAccountId;
    }

    /**
     * Set the ID of the transaction.
     *
     * @param id The new ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the creation time of the transaction.
     *
     * @param creationTime The new creation time to set.
     */
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Returns a string representation of the transaction information.
     *
     * @return A formatted string containing transaction details.
     */
    @Override
    public String toString() {
        return "Transaction ID: " + id + "\n" +
                "Type: " + type + "\n" +
                "Creation Time: " + creationTime + "\n" +
                "Amount: " + amount + "\n";
    }

    /**
     * An enumeration representing transaction types: DEBIT and CREDIT.
     */
    public static enum TransactionType {
        DEBIT,
        CREDIT
    }
}
