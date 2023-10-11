package repositoriesImpl.modelsRepoImpl;

import model.Player;
import modelRepositoriesI.PlayerRepositoryI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the PlayerRepositoryI interface for managing Player objects in memory.
 *
 * This class provides methods for storing, retrieving, and managing Player objects.
 * @author Gleb Nickolaenko
 */
public class PlayerRepository implements PlayerRepositoryI {

    private final Map<String, Player> memory = new HashMap<>();

    /**
     * Saves a Player object to the repository.
     *
     * @param player The Player object to be saved.
     * @return The saved Player object.
     */
    @Override
    public Player save(Player player) {
        return memory.put(player.getLogin(), player);
    }

    /**
     * Retrieves a Player object by their login identifier.
     *
     * @param login The login identifier of the Player to retrieve.
     * @return The Player object associated with the given login identifier, or null if not found.
     */
    @Override
    public Player getByLogin(String login) {
        return memory.get(login);
    }

    /**
     * Retrieves a list of all Player objects in the repository.
     *
     * @return A list of all Player objects stored in the repository.
     */
    @Override
    public List<Player> getAll() {
        return memory.values().stream().toList();
    }
}
