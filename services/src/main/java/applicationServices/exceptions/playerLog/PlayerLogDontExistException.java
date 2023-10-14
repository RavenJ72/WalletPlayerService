package applicationServices.exceptions.playerLog;

import applicationServices.exceptions.BaseException;

/**
 * Exception for non-existing player log entries.
 *
 * @author Gleb Nickolaenko
 */
public class PlayerLogDontExistException extends BaseException {
    public PlayerLogDontExistException(String message) {
        super(message);
    }
}
