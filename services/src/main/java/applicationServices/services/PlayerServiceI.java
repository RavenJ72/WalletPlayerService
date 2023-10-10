package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.Player;
import model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface PlayerServiceI {

    Player createPlayer(Player player) throws BaseException;

    Player findPlayerByLogin(String login) throws BaseException;


    BigDecimal checkBalance(String bankAccountId) throws BaseException;

    List<Transaction> getTransactionHistory(String bankAccountId) throws BaseException;

    List<Player> getAll();

    boolean withdrawMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException;

    boolean depositMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException;




}
