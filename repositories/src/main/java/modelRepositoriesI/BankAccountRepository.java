package modelRepositoriesI;

import model.BankAccount;

import java.math.BigDecimal;

/**
 * This interface represents a repository for managing bank accounts.
 * It provides methods for saving, retrieving, and managing transactions in bank accounts.
 *
 * @author Gleb Nickolaenko
 */
public interface BankAccountRepository {

    /**
     * Saves a bank account in the repository.
     *
     * @param bankAccount the bank account to be saved.
     * @return the saved bank account.
     */
    BankAccount save(BankAccount bankAccount);

    /**
     * Finds a bank account by its unique ID.
     *
     * @param id the unique ID of the bank account to find.
     * @return the found bank account, or null if not found.
     */
    BankAccount findById(Long id);

    /**
     * Deposits a specified amount of money into a bank account.
     *
     * @param bankAccountId the ID of the bank account where the money will be deposited.
     * @param amount the amount of money to deposit.
     * @param transactionId the ID of the transaction.
     * @return true if the deposit was successful, false otherwise.
     */
    boolean depositMoney(Long bankAccountId, BigDecimal amount, Long transactionId);

    /**
     * Withdraws a specified amount of money from a bank account.
     *
     * @param bankAccountId the ID of the bank account from which the money will be withdrawn.
     * @param amount the amount of money to withdraw.
     * @param transactionId the ID of the transaction.
     * @return true if the withdrawal was successful, false otherwise.
     */
    boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId);
}
