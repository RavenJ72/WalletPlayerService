package modelRepositoriesI;

import model.Player;

import java.math.BigInteger;
import java.util.List;

/**
 * An interface representing a repository for managing player entities.
 *
 * This repository provides methods for saving and retrieving player entities.
 *
 * @author Gleb Nickolaenko
 */
public interface PlayerRepositoryI {
    /**
     * Saves a player entity in the repository.
     *
     * @param player The player entity to be saved.
     * @return The saved player entity.
     */
    Player save(Player player);

    /**
     * Retrieves a player entity by their login.
     *
     * @param login The login of the player to retrieve.
     * @return The player entity with the specified login, or null if not found.
     */
    Player getByLogin(String login);

    /**
     * Retrieves a list of all player entities in the repository.
     *
     * @return A list of all player entities.
     */
    List<Player> getAll();
}
