package repositoriesImpl.repoFactories;

import modelRepositoriesI.PlayerRepositoryI;
import repositoriesImpl.modelsRepoImpl.PlayerRepository;

public final class PlayerRepositoryFactory {

    private static PlayerRepositoryI playerRepositoryInstance;

    public static PlayerRepositoryI getPlayerRepository() {
        if (playerRepositoryInstance == null) {
            playerRepositoryInstance = new PlayerRepository();
        }
        return playerRepositoryInstance;
    }
}

