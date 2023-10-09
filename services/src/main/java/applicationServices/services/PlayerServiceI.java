package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.Player;

import java.math.BigDecimal;

public interface PlayerServiceI {

    Player createPlayer(Player player) throws BaseException;

    Player findPlayerByLogin(String login) throws BaseException;

    boolean withdrawMoney(String bankAccountId, BigDecimal amount) throws BaseException;

    boolean depositMoney(String bankAccountId, BigDecimal amount) throws BaseException;



}
