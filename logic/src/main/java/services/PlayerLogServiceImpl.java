package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.playerLog.PlayerLogDontExistException;
import applicationServices.services.PlayerLogService;
import model.PlayerLog;
import modelRepositoriesI.PlayerLogRepository;

import java.util.List;

/**
 * A service class for managing player log entries and related operations.
 *
 * This service class provides methods for creating and retrieving player log entries.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerLogServiceImpl implements PlayerLogService {
    private final PlayerLogRepository playerLogRepository;

    /**
     * Constructs a new PlayerLogService with the specified repository.
     *
     * @param playerLogRepository The repository for managing player log entries.
     */
    public PlayerLogServiceImpl(PlayerLogRepository playerLogRepository) {
        this.playerLogRepository = playerLogRepository;
    }

    /**
     * Creates or updates a player log entry in the system.
     *
     * @param playerLog The player log entry to save or update.
     * @return The saved or updated player log entry.
     */
    @Override
    public PlayerLog save(PlayerLog playerLog) {
        return playerLogRepository.save(playerLog);
    }

    /**
     * Retrieves player log entries for a specific player by their login.
     *
     * @param login The login of the player to retrieve log entries for.
     * @return A list of player log entries for the specified player.
     * @throws BaseException if there are no log entries for the specified player.
     */
    @Override
    public List<PlayerLog> getByLogin(String login) throws BaseException {
        List<PlayerLog> playerLogList = playerLogRepository.getByPlayerLogsByLogin(login);

        if (playerLogList == null || playerLogList.isEmpty()) {
            throw new PlayerLogDontExistException("No log entries exist for the specified player.");
        }
        return playerLogList;
    }
}
