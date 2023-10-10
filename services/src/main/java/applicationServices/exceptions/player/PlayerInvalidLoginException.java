package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

public class PlayerInvalidLoginException extends BaseException {
    public PlayerInvalidLoginException(String message) {
        super(message);
    }
}
