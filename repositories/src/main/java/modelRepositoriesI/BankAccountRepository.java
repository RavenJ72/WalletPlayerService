package modelRepositoriesI;

import model.BankAccount;

import java.math.BigDecimal;

/**
 * An interface representing a repository for managing bank accounts.
 *
 * This repository provides methods for saving and retrieving bank accounts.
 *
 * @author Gleb Nickolaenko
 */
public interface BankAccountRepository {
    /**
     * Saves a bank account in the repository.
     *
     * @param bankAccount The bank account to be saved.
     * @return The saved bank account.
     */
    BankAccount save(BankAccount bankAccount);

    /**
     * Finds a bank account by its unique ID.
     *
     * @param id The unique ID of the bank account to find.
     * @return The found bank account, or null if not found.
     */
    BankAccount findById(Long id);

    boolean depositMoney(Long bankAccountId, BigDecimal amount, Long transactionId);
    boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId);
}
