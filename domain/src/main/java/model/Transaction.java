package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class representing a financial transaction.
 *
 * @author Gleb Nickolaenko
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Long id;
    private String type;
    private LocalDateTime creationTime;
    private BigDecimal amount;
    private Long bankAccountId;



    public Transaction(Long id, String type, BigDecimal amount, Long bankAccountId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.bankAccountId = bankAccountId;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + id + "\n" +
                "Type: " + type + "\n" +
                "Creation Time: " + creationTime + "\n" +
                "Amount: " + amount + "\n";
    }
}
