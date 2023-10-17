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
 * This service class provides methods for creating, retrieving, and managing player accounts,
 * as well as performing financial transactions and checking account balances.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final BankAccountRepository bankAccountRepository;

    private final TransactionRepository transactionRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Player createPlayer(Player player) throws BaseException{
        Player playerInJBDC = playerRepository.save(player);
        if(playerInJBDC == null){
            throw new PlayerNotUniqLoginException("A player with this username already exists");
        }
        return playerInJBDC;
    }


    @Override
    public Player findPlayerByLogin(String login) throws BaseException{
        Player player = playerRepository.getByLogin(login);
        if(player == null){
            throw new PlayerInvalidLoginException("A player with this username does not exist");
        }
        return player;
    }


    @Override
    public BigDecimal checkBalance(Long bankAccountId) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("There is no account with such ids");
        }
        return bankAccount.getBalance();
    }

    @Override
    public List<Transaction> getTransactionHistory(Long bankAccountId) throws BaseException {
        List<Transaction> transactions =  transactionRepository.getAllById(bankAccountId);
        if(transactions == null){
            throw new BankAccountNotFoundException("There is no account with such ids");
        }
        return transactions;
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.getAll();
    }

    @Override
    public boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId) throws BaseException {

        if(!bankAccountRepository.withdrawMoney(bankAccountId,amount,transactionId)){
            throw new PlayerInvalidEnteredDataException("Error of entered data");
        }
        return true;
    }

    @Override
    public boolean depositMoney(Long bankAccountId, BigDecimal amount, Long transactionId) throws BaseException{
        if(!bankAccountRepository.depositMoney(bankAccountId,amount,transactionId)){
            throw new PlayerInvalidEnteredDataException("Error of entered data");
        }
        return true;

    }


}

