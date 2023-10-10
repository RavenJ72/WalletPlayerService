package repositoriesImpl.modelsRepoImpl;

import model.Player;
import model.Transaction;
import modelRepositoriesI.PlayerRepositoryI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepository implements PlayerRepositoryI {

    private final Map<String, Player> memory = new HashMap<>();

    @Override
    public Player save(Player player) {
        return memory.put(player.getLogin(),player);
    }

    @Override
    public Player getByLogin(String login) {
        return memory.get(login);
    }

    @Override
    public List<Player> getAll() {
        return memory.values().stream().toList();
    }
}
