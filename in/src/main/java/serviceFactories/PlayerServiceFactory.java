package serviceFactories;

import applicationServices.services.PlayerServiceI;
import repositoriesImpl.repoFactories.PlayerRepositoryFactory;
import services.PlayerService;

/**
 * A factory class responsible for providing a single instance of the PlayerService.
 *
 * This class ensures that only one instance of the PlayerService is created and reused for efficiency.
 * It retrieves the necessary repository and bank account service to initialize the PlayerService.
 * @author Gleb Nickolaenko
 */
public final class PlayerServiceFactory {

    private static PlayerServiceI playerServiceInstance;

    /**
     * Retrieves the single instance of the PlayerService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerService.
     */
    public static PlayerServiceI getPlayerService() {
        if (playerServiceInstance == null) {
            playerServiceInstance = new PlayerService(
                    PlayerRepositoryFactory.getPlayerRepository(),
                    BankAccountServiceFactory.getBankAccountService()
            );
        }
        return playerServiceInstance;
    }
}
