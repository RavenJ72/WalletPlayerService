package repositoriesImpl.repoFactories;

import modelRepositoriesI.PlayerLogRepositoryI;
import repositoriesImpl.logsRepoImpl.PlayerLogRepository;

/**
 * A factory class responsible for providing a single instance of the PlayerLogRepository.
 *
 * This class ensures that only one instance of the PlayerLogRepository is created and reused for efficiency.
 * It provides access to the repository for player log-related operations.
 * @author Gleb Nickolaenko
 */
public final class PlayerLogRepositoryFactory {

    private static PlayerLogRepositoryI playerLogRepositoryInstance;

    /**
     * Retrieves the single instance of the PlayerLogRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerLogRepository.
     */
    public static PlayerLogRepositoryI getPlayerLogRepository() {
        if (playerLogRepositoryInstance == null) {
            playerLogRepositoryInstance = new PlayerLogRepository();
        }
        return playerLogRepositoryInstance;
    }
}
