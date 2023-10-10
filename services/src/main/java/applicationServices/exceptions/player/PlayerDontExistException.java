package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

public class PlayerDontExistException extends BaseException {
    public PlayerDontExistException(String message) {
        super(message);
    }
}
