package modelRepositoriesI;

import model.PlayerLog;

import java.util.List;

public interface PlayerLogRepositoryI {

     PlayerLog save(PlayerLog playerLog);
     List<PlayerLog> getByPlayerLogsByLogin(String login);


}
