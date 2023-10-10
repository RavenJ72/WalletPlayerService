package consoleUI.serviceFactories;

import applicationServices.services.PlayerLogServiceI;
import repositoriesImpl.repoFactories.PlayerLogRepositoryFactory;
import services.PlayerLogService;

public final class PlayerLogServiceFactory {

    private static PlayerLogServiceI playerLogServiceInstance;

    public static PlayerLogServiceI getPlayerLogService() {
        if (playerLogServiceInstance == null) {
            playerLogServiceInstance = new PlayerLogService(PlayerLogRepositoryFactory.getPlayerLogRepository());
        }
        return playerLogServiceInstance;
    }
}

