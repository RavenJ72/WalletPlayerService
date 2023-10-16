package inMemoryRepositories.logsRepoImpl;

import model.PlayerLog;
import modelRepositoriesI.PlayerLogRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the PlayerLogRepositoryI interface for managing PlayerLog objects in memory.
 *
 * This class provides methods for saving and retrieving PlayerLog objects, as well as querying logs by login.
 * @author Gleb Nickolaenko
 */
public class PlayerLogRepositoryImpl implements PlayerLogRepository {
    private final Map<String, List<PlayerLog>> memory = new HashMap<>();

    /**
     * Saves a PlayerLog object to the repository.
     *
     * @param playerLog The PlayerLog object to be saved.
     * @return The saved PlayerLog object.
     */
    @Override
    public PlayerLog save(PlayerLog playerLog) {
        return put(playerLog);
    }

    /**
     * Retrieves a list of PlayerLog objects by a login.
     *
     * @param login The login associated with the logs to retrieve.
     * @return A list of PlayerLog objects related to the given login, or null if not found.
     */
    @Override
    public List<PlayerLog> getByPlayerLogsByLogin(String login) {
        return memory.get(login);
    }

    private PlayerLog put(PlayerLog playerLog) {
        memory.putIfAbsent(playerLog.getLogin(), new ArrayList<>());
        List<PlayerLog> valueList = memory.get(playerLog.getLogin());
        valueList.add(playerLog);
        memory.put(playerLog.getLogin(), valueList);
        return playerLog;
    }
}
