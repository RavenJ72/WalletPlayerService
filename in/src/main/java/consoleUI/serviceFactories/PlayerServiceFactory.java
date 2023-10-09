package consoleUI.serviceFactories;

import applicationServices.services.PlayerServiceI;
import modelRepositoriesI.PlayerRepositoryI;
import repositoriesImpl.repoFactories.PlayerRepositoryFactory;
import services.PlayerService;

public final class PlayerServiceFactory {

    private static PlayerServiceI playerServiceInstance;

    public static PlayerServiceI getPlayerService(PlayerRepositoryI playerRepository) {
        if (playerServiceInstance == null) {
            playerServiceInstance = new PlayerService(PlayerRepositoryFactory.getPlayerRepository());
        }
        return playerServiceInstance;
    }
}
