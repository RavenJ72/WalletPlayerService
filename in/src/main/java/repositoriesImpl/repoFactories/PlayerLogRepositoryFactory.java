package repositoriesImpl.repoFactories;

import modelRepositoriesI.PlayerLogRepositoryI;
import repositoriesImpl.logsRepoImpl.PlayerLogRepository;

public final class PlayerLogRepositoryFactory {

    private static PlayerLogRepositoryI playerLogRepositoryInstance;

    public static PlayerLogRepositoryI getPlayerLogRepository() {
        if (playerLogRepositoryInstance == null) {
            playerLogRepositoryInstance = new PlayerLogRepository();
        }
        return playerLogRepositoryInstance;
    }
}
