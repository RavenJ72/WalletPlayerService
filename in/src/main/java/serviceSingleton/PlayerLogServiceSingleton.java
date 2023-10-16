package serviceSingleton;

import applicationServices.services.PlayerLogService;
import repoSingleton.PlayerLogRepositorySingleton;
import services.PlayerLogServiceImpl;

/**
 * A singleton class responsible for providing a single instance of the PlayerLogService.
 *
 * This class ensures that only one instance of the PlayerLogService is created and reused for efficiency.
 * It retrieves the necessary repository to initialize the PlayerLogService.
 *
 * Usage:
 * To obtain the single instance of the PlayerLogService, use the getPlayerLogService() method.
 *
 * Example:
 * PlayerLogServiceI playerLogService = PlayerLogServiceSingleton.getPlayerLogService();
 *
 * @author Gleb Nickolaenko
 */
public final class PlayerLogServiceSingleton {

    private static PlayerLogService playerLogServiceInstance;

    /**
     * Retrieves the single instance of the PlayerLogService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerLogService.
     */
    public static PlayerLogService getPlayerLogService() {
        if (playerLogServiceInstance == null) {
            playerLogServiceInstance = new PlayerLogServiceImpl(PlayerLogRepositorySingleton.getPlayerLogRepository());
        }
        return playerLogServiceInstance;
    }
}
