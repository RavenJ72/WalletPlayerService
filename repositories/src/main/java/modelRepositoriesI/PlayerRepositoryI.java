package modelRepositoriesI;

import model.Player;

public interface PlayerRepositoryI {
    Player save(Player player);
    Player getByLogin(String login);


}
