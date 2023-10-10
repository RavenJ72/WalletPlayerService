package repositoriesImpl.repoFactories;

import model.BankAccount;
import model.Player;
import modelRepositoriesI.PlayerRepositoryI;
import repositoriesImpl.modelsRepoImpl.PlayerRepository;

public final class PlayerRepositoryFactory {

    private static PlayerRepositoryI playerRepositoryInstance;

    public static PlayerRepositoryI getPlayerRepository() {
        if (playerRepositoryInstance == null) {
            playerRepositoryInstance = new PlayerRepository();

            Player player = new Player("admin","admin",null);
            player.setPlayerRole("ADMIN");
            playerRepositoryInstance.save(player);

        }
        return playerRepositoryInstance;
    }
}

