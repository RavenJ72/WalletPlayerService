package consoleUI.serviceFactories;

import applicationServices.services.PlayerServiceI;
import repositoriesImpl.repoFactories.PlayerRepositoryFactory;
import services.PlayerService;

public final class PlayerServiceFactory {

    private static PlayerServiceI playerServiceInstance;

    public static PlayerServiceI getPlayerService() {
        if (playerServiceInstance == null) {
            playerServiceInstance = new PlayerService(PlayerRepositoryFactory.getPlayerRepository(), BankAccountServiceFactory.getBankAccountService());
        }
        return playerServiceInstance;
    }
}
