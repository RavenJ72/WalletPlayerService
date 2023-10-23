package applicationServices.exceptions.player;

import applicationServices.exceptions.BaseException;

public class PlayerInvalidEnteredDataException extends BaseException {
    /**
     * Constructs a new BaseException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public PlayerInvalidEnteredDataException(String message) {
        super(message);
    }
}
