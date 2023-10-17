package repoSingleton;

import jbdcRepositories.PlayerLogRepositoryImpl;
import modelRepositoriesI.PlayerLogRepository;


/**
 * A singleton class responsible for providing a single instance of the PlayerLogRepository.
 * This class ensures that only one instance of the PlayerLogRepository is created and reused for efficiency.
 * It provides access to the repository for player log-related operations.
 * Usage:
 * To obtain the single instance of the PlayerLogRepository, use the getPlayerLogRepository() method.
 * Example:
 * PlayerLogRepositoryI playerLogRepository = PlayerLogRepositorySingleton.getPlayerLogRepository();
 *
 * @author Gleb Nickolaenko
 */
public final class PlayerLogRepositorySingleton {

    private static PlayerLogRepository playerLogRepositoryInstance;

    /**
     * Retrieves the single instance of the PlayerLogRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerLogRepository.
     */
    public static PlayerLogRepository getPlayerLogRepository() {
        if (playerLogRepositoryInstance == null) {
            playerLogRepositoryInstance = new PlayerLogRepositoryImpl();
        }
        return playerLogRepositoryInstance;
    }
}
