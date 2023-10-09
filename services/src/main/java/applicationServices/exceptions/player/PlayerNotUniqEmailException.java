package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

public class PlayerNotUniqEmailException extends BaseException {
    public PlayerNotUniqEmailException(String message) {
        super(message);
    }
}
