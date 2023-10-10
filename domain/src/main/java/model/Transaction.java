package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String id;

    private String type;
    private LocalDateTime creationTime;
    private BigDecimal amount;
    private String bankAccountId;

    public Transaction( String type, BigDecimal amount,String bankAccountId) {
        this.id = generateUniqueId();
        this.type = type;
        this.creationTime = LocalDateTime.now();
        this.amount = amount;
        this.bankAccountId = bankAccountId;
    }



    public static enum TransactionType {
        DEBIT,
        CREDIT
    }

    public String getId() {
        return id;
    }



    public String getType() {
        return type;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Transaction ID: " + id + "\n" +
                "Type: " + type + "\n" +
                "Creation Time: " + creationTime + "\n" +
                "Amount: " + amount + "\n";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
