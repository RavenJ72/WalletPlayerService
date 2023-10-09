package services;

import applicationServices.exceptions.BaseException;
import applicationServices.services.PlayerServiceI;
import model.Player;
import modelRepositoriesI.PlayerRepositoryI;

import java.math.BigDecimal;

public class PlayerService implements PlayerServiceI {

    private final PlayerRepositoryI playerRepository;

    public PlayerService(PlayerRepositoryI playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(Player player) throws BaseException {
        return playerRepository.save(player);
    }

    @Override
    public Player findPlayerByLogin(String login) throws BaseException {
        return null;
    }

    @Override
    public boolean withdrawMoney(String bankAccountId, BigDecimal amount) throws BaseException {
        return false;
    }

    @Override
    public boolean depositMoney(String bankAccountId, BigDecimal amount) throws BaseException {
        return false;
    }
}
