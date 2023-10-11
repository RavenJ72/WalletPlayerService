package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 * An interface representing a service for managing bank accounts.
 *
 * This service provides methods for saving, retrieving, and managing bank accounts. It also supports operations such as
 * depositing and withdrawing money from bank accounts.
 *
 * @author Gleb Nickolaenko
 */
public interface BankAccountServiceI {
    /**
     * Saves a bank account in the repository.
     *
     * @param bankAccount The bank account to be saved.
     * @return The saved bank account.
     * @throws BaseException If an error occurs during the operation.
     */
    BankAccount save(BankAccount bankAccount) throws BaseException;

    /**
     * Retrieves a bank account by its unique ID.
     *
     * @param id The unique ID of the bank account to retrieve.
     * @return The found bank account, or null if not found.
     * @throws BaseException If an error occurs during the operation.
     */
    BankAccount findAccountById(String id) throws BaseException;

    /**
     * Withdraws a specified amount of money from a bank account.
     *
     * @param bankAccountId The ID of the bank account to withdraw money from.
     * @param amountString  The amount to withdraw.
     * @param transactionId The unique ID of the transaction.
     * @return True if the withdrawal is successful, false otherwise.
     * @throws BaseException If an error occurs during the operation.
     */
    boolean withdrawMoney(String bankAccountId, BigDecimal amountString, String transactionId) throws BaseException;

    /**
     * Deposits a specified amount of money into a bank account.
     *
     * @param bankAccountId The ID of the bank account to deposit money into.
     * @param amountString  The amount to deposit.
     * @param transactionId The unique ID of the transaction.
     * @return True if the deposit is successful, false otherwise.
     * @throws BaseException If an error occurs during the operation.
     */
    boolean depositMoney(String bankAccountId, BigDecimal amountString, String transactionId) throws BaseException;
}
