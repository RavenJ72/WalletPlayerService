package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.PlayerLog;

import java.util.List;

public interface PlayerLogServiceI {
    PlayerLog save(PlayerLog playerLog);
    List<PlayerLog> getByLogin(String login) throws BaseException;


}
