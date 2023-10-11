package serviceFactories;

import applicationServices.services.PlayerLogServiceI;
import repositoriesImpl.repoFactories.PlayerLogRepositoryFactory;
import services.PlayerLogService;

/**
 * A factory class responsible for providing a single instance of the PlayerLogService.
 *
 * This class ensures that only one instance of the PlayerLogService is created and reused for efficiency.
 * It retrieves the necessary repository to initialize the PlayerLogService.
 * @author Gleb Nickolaenko
 */
public final class PlayerLogServiceFactory {

    private static PlayerLogServiceI playerLogServiceInstance;

    /**
     * Retrieves the single instance of the PlayerLogService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerLogService.
     */
    public static PlayerLogServiceI getPlayerLogService() {
        if (playerLogServiceInstance == null) {
            playerLogServiceInstance = new PlayerLogService(PlayerLogRepositoryFactory.getPlayerLogRepository());
        }
        return playerLogServiceInstance;
    }
}
