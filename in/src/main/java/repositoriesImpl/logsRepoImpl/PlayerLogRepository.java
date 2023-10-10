package repositoriesImpl.logsRepoImpl;


import model.PlayerLog;
import modelRepositoriesI.PlayerLogRepositoryI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerLogRepository implements PlayerLogRepositoryI {
    private final Map<String, List<PlayerLog>> memory = new HashMap<>();

    @Override
    public PlayerLog save(PlayerLog playerLog) {
        return put(playerLog);
    }

    @Override
    public List<PlayerLog> getByPlayerLogsByLogin(String login) {
        return memory.get(login);
    }

    private PlayerLog put(PlayerLog playerLog) {
        memory.putIfAbsent(playerLog.getLogin(), new ArrayList<>());
        List<PlayerLog> valueList = memory.get(playerLog.getLogin());
        valueList.add(playerLog);
        memory.put(playerLog.getLogin(), valueList);
        return playerLog;
    }
}
