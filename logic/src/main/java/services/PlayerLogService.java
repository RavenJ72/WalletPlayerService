package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.playerLog.PlayerLogDontExistException;
import applicationServices.services.PlayerLogServiceI;
import model.PlayerLog;
import modelRepositoriesI.PlayerLogRepositoryI;

import java.util.List;

public class PlayerLogService implements PlayerLogServiceI {
    private final PlayerLogRepositoryI playerLogRepository;

    public PlayerLogService(PlayerLogRepositoryI playerLogRepository) {
        this.playerLogRepository = playerLogRepository;
    }

    @Override
    public PlayerLog save(PlayerLog playerLog){
        return playerLogRepository.save(playerLog);
    }

    @Override
    public List<PlayerLog> getByLogin(String login) throws BaseException {
        List<PlayerLog> playerLogList = playerLogRepository.getByPlayerLogsByLogin(login);

        if(playerLogList == null){
            throw new PlayerLogDontExistException("Log does not exist.");
        }
        return playerLogRepository.getByPlayerLogsByLogin(login);
    }


}
