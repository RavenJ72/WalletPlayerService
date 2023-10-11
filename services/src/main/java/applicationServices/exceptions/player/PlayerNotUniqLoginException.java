package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

/**
 * Exception for non-unique player logins.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerNotUniqLoginException extends BaseException {
    public PlayerNotUniqLoginException(String message) {
        super(message);
    }
}
