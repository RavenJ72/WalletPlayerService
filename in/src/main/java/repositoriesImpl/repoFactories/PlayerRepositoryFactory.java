package repositoriesImpl.repoFactories;

import model.Player;
import modelRepositoriesI.PlayerRepositoryI;
import repositoriesImpl.modelsRepoImpl.PlayerRepository;

/**
 * A factory class responsible for providing a single instance of the PlayerRepository.
 *
 * This class ensures that only one instance of the PlayerRepository is created and reused for efficiency.
 * It provides access to the repository for player-related operations.
 * @author Gleb Nickolaenko
 */
public final class PlayerRepositoryFactory {

    private static PlayerRepositoryI playerRepositoryInstance;

    /**
     * Retrieves the single instance of the PlayerRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the PlayerRepository.
     */
    public static PlayerRepositoryI getPlayerRepository() {
        if (playerRepositoryInstance == null) {
            playerRepositoryInstance = new PlayerRepository();

            // Initialize with an admin user if the repository is created for the first time
            Player player = new Player("admin", "admin", null);
            player.setPlayerRole("ADMIN");
            playerRepositoryInstance.save(player);
        }
        return playerRepositoryInstance;
    }
}
