package serviceSingleton;

import applicationServices.services.PlayerService;
import repoSingleton.BankAccountRepositorySingleton;
import repoSingleton.PlayerRepositorySingleton;
import repoSingleton.TransactionRepoSingleton;
import services.PlayerServiceImpl;

/**
 * A singleton class responsible for providing a single instance of the PlayerService.
 *
 * This class ensures that only one instance of the PlayerService is created and reused for efficiency.
 * It retrieves the necessary repository and bank account service to initialize the PlayerService.
 *
 * Usage:
 * To obtain the single instance of the PlayerService, use the getPlayerService() method.
 *
 * Example:
 * PlayerServiceI playerService = PlayerServiceSingleton.getPlayerService();
 *
 * @author Gleb Nickolaenko
 */
public final class PlayerServiceSingleton {

    private static PlayerService playerServiceInstance;

    /**
     * Retrieves the single instance of the PlayerService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerService.
     */
    public static PlayerService getPlayerService() {
        if (playerServiceInstance == null) {
            playerServiceInstance = new PlayerServiceImpl(
                    PlayerRepositorySingleton.getPlayerRepository(), BankAccountRepositorySingleton.getBankAccountRepository(), TransactionRepoSingleton.getTransactionRepository());
        }
        return playerServiceInstance;
    }
}
