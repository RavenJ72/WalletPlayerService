package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String state;
    private String type;
    private LocalDateTime creationTime;
    private BigDecimal amount;
    private String senderLogin;

    public Transaction(String id, String state, String type, BigDecimal amount,String senderLogin) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.creationTime = LocalDateTime.now();
        this.amount = amount;
        this.senderLogin = senderLogin;
    }

    public static enum TransactionState {
        SUCCESSFUL,
        FAILURE,
        PENDING
    }

    public static enum TransactionType {
        DEBIT,
        CREDIT
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
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

    public String getSenderLogin() {
        return senderLogin;
    }
}
