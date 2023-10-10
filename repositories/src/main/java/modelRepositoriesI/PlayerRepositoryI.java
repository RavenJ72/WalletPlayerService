package modelRepositoriesI;

import model.Player;

import java.math.BigInteger;

public interface PlayerRepositoryI {
    Player save(Player player);
    Player getByLogin(String login);


}
