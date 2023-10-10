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

public class PlayerService implements PlayerServiceI {

    private final PlayerRepositoryI playerRepository;
    private final BankAccountServiceI bankAccountService;

    public PlayerService(PlayerRepositoryI playerRepository, BankAccountServiceI bankAccountService) {
        this.playerRepository = playerRepository;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public Player createPlayer(Player player) throws BaseException {
        if(playerRepository.getByLogin(player.getLogin()) == null){
            if(validateLogin(player.getLogin())){
                BankAccount bankAccount = new BankAccount();
                player.setBankAccountId(bankAccount.getId());
                playerRepository.save(player);
            }else{
                throw new PlayerInvalidLoginException("Incorrect login format");
            }
        }else{
            throw new PlayerNotUniqLoginException("A user with this username already exists.");
        }
        return playerRepository.save(player);
    }

    @Override
    public Player findPlayerByLogin(String login) throws BaseException {
        Player player = playerRepository.getByLogin(login);
        if(player == null){
            throw new PlayerDontExistException("Issued player dont exists");
        }
        return player;
    }

    @Override
    public BigDecimal checkBalance(String bankAccountId) throws BaseException {
        return bankAccountService.findAccountById(bankAccountId).getBalance();
    }

    @Override
    public List<Transaction> getTransactionHistory(String bankAccountId) throws BaseException {
        return bankAccountService.findAccountById(bankAccountId).getPlayerTransactions();
    }

    @Override
    public boolean withdrawMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException {
        return bankAccountService.withdrawMoney(bankAccountId,amount,transactionId);
    }

    @Override
    public boolean depositMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException {
        return bankAccountService.depositMoney(bankAccountId,amount,transactionId);
    }

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

        // If all checks pass, the string is valid
        return true;
    }

}
