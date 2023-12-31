package applicationServices.services;


import applicationServices.exceptions.BaseException;
import model.Player;
import model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * An interface representing a service for managing player entities and financial transactions.
 *
 * This service provides methods for creating, retrieving, and managing player entities. It also supports operations such as
 * checking player balances, retrieving transaction histories, and managing money deposits and withdrawals.
 *
 * @author Gleb Nickolaenko
 */
public interface PlayerService {
    /**
     * Creates a new player entity.
     *
     * @param player The player entity to be created.
     * @return The created player entity.

     */
    Player createPlayer(Player player) throws BaseException;

    /**
     * Retrieves a player entity by their login.
     *
     * @param login The login of the player to retrieve.
     * @return The player entity with the specified login, or null if not found.

     */
    Player findPlayerByLogin(String login) throws BaseException;

    /**
     * Checks the balance of a bank account.
     *
     * @param bankAccountId The ID of the bank account to check the balance.
     * @return The current balance of the bank account.

     */
    BigDecimal checkBalance(Long bankAccountId) throws BaseException;

    /**
     * Retrieves the transaction history for a bank account.
     *
     * @param bankAccountId The ID of the bank account to retrieve the transaction history.
     * @return A list of financial transactions associated with the bank account.
     */
    List<Transaction> getTransactionHistory(Long bankAccountId) throws BaseException;

    /**
     * Retrieves a list of all player entities.
     *
     * @return A list of all player entities in the repository.
     */
    List<Player> getAll();

    /**
     * Withdraws a specified amount of money from a bank account.
     *
     * @param bankAccountId The ID of the bank account to withdraw money from.
     * @param amount The amount to withdraw.
     * @param transactionId The unique ID of the transaction.
     * @return True if the withdrawal is successful, false otherwise.
     */
    boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId) throws BaseException;

    /**
     * Deposits a specified amount of money into a bank account.
     *
     * @param bankAccountId The ID of the bank account to deposit money into.
     * @param amount The amount to deposit.
     * @param transactionId The unique ID of the transaction.
     * @return True if the deposit is successful, false otherwise.
     */
    boolean depositMoney(Long bankAccountId, BigDecimal amount, Long transactionId) throws BaseException;


}
