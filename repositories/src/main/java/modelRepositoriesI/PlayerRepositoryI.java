package modelRepositoriesI;

import model.Player;

import java.math.BigInteger;
import java.util.List;

public interface PlayerRepositoryI {
    Player save(Player player);
    Player getByLogin(String login);

    List<Player> getAll();



}
