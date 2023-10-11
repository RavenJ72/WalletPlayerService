package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

/**
 * Exception for non-existing players.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerDontExistException extends BaseException {
    public PlayerDontExistException(String message) {
        super(message);
    }
}
