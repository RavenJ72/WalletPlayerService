package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class representing a bank account.
 * @author Gleb Nickolaenko
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    private Long id;
    private BigDecimal balance;


}
