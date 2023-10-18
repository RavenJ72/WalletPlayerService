package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.exceptions.player.PlayerInvalidEnteredDataException;
import applicationServices.exceptions.player.PlayerInvalidLoginException;
import applicationServices.exceptions.player.PlayerNotUniqLoginException;
import applicationServices.services.PlayerService;
import model.BankAccount;
import model.Player;
import model.Transaction;
import modelRepositoriesI.BankAccountRepository;
import modelRepositoriesI.PlayerRepository;
import modelRepositoriesI.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * A service class for managing player accounts and related operations.
 *
 * This class provides methods for creating, retrieving, and managing player accounts,
 * performing financial transactions, checking account balances, and retrieving transaction history.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    /**
     * Constructs a new PlayerService with the specified repositories.
     *
     * @param playerRepository The repository for managing player information.
     * @param bankAccountRepository The repository for managing bank account information.
     * @param transactionRepository The repository for managing transaction information.
     */
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             BankAccountRepository bankAccountRepository,
                             TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Creates a new player in the system.
     *
     * @param player The player to be created.
     * @return The created player.
     * @throws PlayerNotUniqLoginException If a player with the specified username already exists.
     */
    @Override
    public Player createPlayer(Player player) throws BaseException{
        Player playerInJBDC = playerRepository.save(player);
        if(playerInJBDC == null){
            throw new PlayerNotUniqLoginException("A player with this username already exists");
        }
        return playerInJBDC;
    }

    /**
     * Retrieves a player by their login.
     *
     * @param login The login of the player to retrieve.
     * @return The retrieved player.
     * @throws PlayerInvalidLoginException If a player with the specified login does not exist.
     */
    @Override
    public Player findPlayerByLogin(String login) throws BaseException{
        Player player = playerRepository.getByLogin(login);
        if(player == null){
            throw new PlayerInvalidLoginException("A player with this login does not exist");
        }
        return player;
    }

    /**
     * Checks the balance of a specified bank account.
     *
     * @param bankAccountId The ID of the bank account to check.
     * @return The balance of the bank account.
     * @throws BankAccountNotFoundException If the specified bank account does not exist.
     */
    @Override
    public BigDecimal checkBalance(Long bankAccountId) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("There is no account with such ID");
        }
        return bankAccount.getBalance();
    }

    /**
     * Retrieves the transaction history of a specified bank account.
     *
     * @param bankAccountId The ID of the bank account to retrieve transactions for.
     * @return A list of transactions associated with the bank account.
     * @throws BankAccountNotFoundException If the specified bank account does not exist.
     */
    @Override
    public List<Transaction> getTransactionHistory(Long bankAccountId) throws BaseException {
        List<Transaction> transactions =  transactionRepository.getAllById(bankAccountId);
        if(transactions == null){
            throw new BankAccountNotFoundException("There is no account with such ID");
        }
        return transactions;
    }

    /**
     * Retrieves a list of all players in the system.
     *
     * @return A list of all players.
     */
    @Override
    public List<Player> getAll() {
        return playerRepository.getAll();
    }

    /**
     * Performs a withdrawal operation from a specified bank account.
     *
     * @param bankAccountId The ID of the bank account to withdraw from.
     * @param amount The amount to withdraw.
     * @param transactionId The ID of the transaction.
     * @return true if the withdrawal operation was successful, false otherwise.
     * @throws PlayerInvalidEnteredDataException If there's an error with the entered data.
     */
    @Override
    public boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId) throws BaseException {

        if(!bankAccountRepository.withdrawMoney(bankAccountId,amount,transactionId)){
            throw new PlayerInvalidEnteredDataException("Error of entered data");
        }
        return true;
    }

    /**
     * Performs a deposit operation to a specified bank account.
     *
     * @param bankAccountId The ID of the bank account to deposit to.
     * @param amount The amount to deposit.
     * @param transactionId The ID of the transaction.
     * @return true if the deposit operation was successful, false otherwise.
     * @throws PlayerInvalidEnteredDataException If there's an error with the entered data.
     */
    @Override
    public boolean depositMoney(Long bankAccountId, BigDecimal amount, Long transactionId) throws BaseException{
        if(!bankAccountRepository.depositMoney(bankAccountId,amount,transactionId)){
            throw new PlayerInvalidEnteredDataException("Error of entered data");
        }
        return true;
    }
}
