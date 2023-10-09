package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

public class PlayerNotUniqLoginException extends BaseException {

    public PlayerNotUniqLoginException(String message) {
        super(message);
    }
}
