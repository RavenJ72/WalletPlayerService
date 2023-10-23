package jbdcRepositories.repoSingleton;

import jbdcRepositories.PlayerRepositoryImpl;
import jbdcRepositories.connection.DatabaseManager;
import modelRepositoriesI.PlayerRepository;


/**
 * A singleton class responsible for providing a single instance of the PlayerRepository.
 *
 * This class ensures that only one instance of the PlayerRepository is created and reused for efficiency.
 * It provides access to the repository for player-related operations.
 *
 * Usage:
 * To obtain the single instance of the PlayerRepository, use the getPlayerRepository() method.
 *
 * Example:
 * PlayerRepositoryI playerRepository = PlayerRepositorySingleton.getPlayerRepository();
 *
 * If the repository is created for the first time, it initializes with an admin user.
 *
 * @author Gleb Nickolaenko
 */
public final class PlayerRepositorySingleton {

    private static PlayerRepository playerRepositoryInstance;

    /**
     * Retrieves the single instance of the PlayerRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerRepository.
     */
    public static PlayerRepository getPlayerRepository() {
        if (playerRepositoryInstance == null) {
            playerRepositoryInstance = new PlayerRepositoryImpl(DatabaseManager.getUrl());

        }
        return playerRepositoryInstance;
    }
}
