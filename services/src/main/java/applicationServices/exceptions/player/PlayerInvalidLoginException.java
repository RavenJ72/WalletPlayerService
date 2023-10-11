package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

/**
 * Exception for invalid player logins.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerInvalidLoginException extends BaseException {
    public PlayerInvalidLoginException(String message) {
        super(message);
    }
}
