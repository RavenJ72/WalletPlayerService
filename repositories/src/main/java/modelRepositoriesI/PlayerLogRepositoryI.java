package modelRepositoriesI;

import model.PlayerLog;

import java.util.List;

/**
 * An interface representing a repository for managing player logs.
 *
 * This repository provides methods for saving player logs and retrieving logs by player login.
 *
 * @author Gleb Nickolaenko
 */
public interface PlayerLogRepositoryI {
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
      */
     List<PlayerLog> getByPlayerLogsByLogin(String login);
}
