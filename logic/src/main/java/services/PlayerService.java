package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.exceptions.player.PlayerDontExistException;
import applicationServices.exceptions.player.PlayerInvalidLoginException;
import applicationServices.exceptions.player.PlayerNotUniqLoginException;
import applicationServices.services.BankAccountServiceI;
import applicationServices.services.PlayerServiceI;
import model.BankAccount;
import model.Player;
import model.Transaction;
import modelRepositoriesI.PlayerRepositoryI;

import java.math.BigDecimal;
import java.util.List;

/**
 * A service class for managing player accounts and related operations.
 *
 * This service class provides methods for creating, retrieving, and managing player accounts,
 * as well as performing financial transactions and checking account balances.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerService implements PlayerServiceI {

    private final PlayerRepositoryI playerRepository;
    private final BankAccountServiceI bankAccountService;

    /**
     * Constructs a new PlayerService with the specified repositories and services.
     *
     * @param playerRepository The repository for managing player accounts.
     * @param bankAccountService The service for managing bank accounts.
     */
    public PlayerService(PlayerRepositoryI playerRepository, BankAccountServiceI bankAccountService) {
        this.playerRepository = playerRepository;
        this.bankAccountService = bankAccountService;
    }

    /**
     * Creates a new player account in the system.
     *
     * @param player The player account to create.
     * @return The created player account.
     * @throws BaseException if the operation encounters an error, such as an invalid login or a duplicate username.
     */
    @Override
    public Player createPlayer(Player player) throws BaseException {
        if (playerRepository.getByLogin(player.getLogin()) == null) {
            if (validateLogin(player.getLogin())) {
                BankAccount bankAccount = new BankAccount();
                bankAccountService.save(bankAccount);
                player.setBankAccountId(bankAccount.getId());
                playerRepository.save(player);
            } else {
                throw new PlayerInvalidLoginException("Invalid login format");
            }
        } else {
            throw new PlayerNotUniqLoginException("A user with this username already exists.");
        }
        return playerRepository.save(player);
    }

    /**
     * Retrieves a player account by their login.
     *
     * @param login The login of the player to retrieve.
     * @return The player account with the specified login.
     * @throws BaseException if the player account doesn't exist.
     */
    @Override
    public Player findPlayerByLogin(String login) throws BaseException {
        Player player = playerRepository.getByLogin(login);
        if (player == null) {
            throw new PlayerDontExistException("The specified player doesn't exist");
        }
        return player;
    }

    /**
     * Checks the balance of a player's bank account.
     *
     * @param bankAccountId The ID of the bank account to check the balance for.
     * @return The balance of the specified bank account.
     * @throws BaseException if the bank account doesn't exist.
     */
    @Override
    public BigDecimal checkBalance(String bankAccountId) throws BaseException {
        return bankAccountService.findAccountById(bankAccountId).getBalance();
    }

    /**
     * Retrieves the transaction history for a player's bank account.
     *
     * @param bankAccountId The ID of the bank account to retrieve the transaction history for.
     * @return A list of transaction records for the specified bank account.
     * @throws BaseException if the bank account doesn't exist.
     */
    @Override
    public List<Transaction> getTransactionHistory(String bankAccountId) throws BaseException {
        return bankAccountService.findAccountById(bankAccountId).getPlayerTransactions();
    }

    /**
     * Withdraws money from a player's bank account.
     *
     * @param bankAccountId The ID of the bank account to withdraw from.
     * @param amount The amount to withdraw.
     * @param transactionId The ID of the transaction (optional).
     * @return true if the withdrawal is successful, false otherwise.
     * @throws BaseException if the operation encounters an error, such as a non-existing account or insufficient funds.
     */
    @Override
    public boolean withdrawMoney(String bankAccountId, BigDecimal amount, String transactionId) throws BaseException {
        return bankAccountService.withdrawMoney(bankAccountId, amount, transactionId);
    }

    /**
     * Deposits money into a player's bank account.
     *
     * @param bankAccountId The ID of the bank account to deposit into.
     * @param amount The amount to deposit.
     * @param transactionId The ID of the transaction (optional).
     * @return true if the deposit is successful, false otherwise.
     * @throws BaseException if the operation encounters an error, such as a non-existing account.
     */
    @Override
    public boolean depositMoney(String bankAccountId, BigDecimal amount, String transactionId) throws BaseException {
        return bankAccountService.depositMoney(bankAccountId, amount, transactionId);
    }

    /**
     * Retrieves a list of all player accounts in the system.
     *
     * @return A list of all player accounts.
     */
    @Override
    public List<Player> getAll() {
        return playerRepository.getAll();
    }

    /**
     * Validates a player's login for correctness.
     *
     * @param login The login to validate.
     * @return true if the login is valid, false otherwise.
     */
    private boolean validateLogin(String login) {
        // Check for null or empty string
        if (login == null || login.isEmpty()) {
            return false;
        }

        // Remove leading and trailing spaces and check length
        login = login.trim();
        if (login.length() > 12) {
            return false;
        }

        // Check that the string is not composed only of spaces
        if (login.matches("\\s+")) {
            return false;
        }

        // If all checks pass, the string is considered valid
        return true;
    }
}

