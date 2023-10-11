package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.PlayerLog;

import java.util.List;

/**
 * An interface representing a service for managing player log entries.
 *
 * This service provides methods for saving and retrieving player log entries, often used for auditing and tracking player actions.
 *
 * @author Gleb Nickolaenko
 */
public interface PlayerLogServiceI {
    /**
     * Saves a player log entry in the repository.
     *
     * @param playerLog The player log entry to be saved.
     * @return The saved player log entry.
     */
    PlayerLog save(PlayerLog playerLog);

    /**
     * Retrieves a list of player log entries by player login.
     *
     * @param login The login of the player whose log entries are to be retrieved.
     * @return A list of player log entries for the specified player.
     * @throws BaseException If an error occurs during the operation.
     */
    List<PlayerLog> getByLogin(String login) throws BaseException;
}
