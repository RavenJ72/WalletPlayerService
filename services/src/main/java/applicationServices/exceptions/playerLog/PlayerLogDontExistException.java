package applicationServices.exceptions.playerLog;

import applicationServices.exceptions.BaseException;

public class PlayerLogDontExistException extends BaseException {
    public PlayerLogDontExistException(String message) {
        super(message);
    }
}


