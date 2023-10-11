package applicationServices.exceptions;

/**
 * A base exception class.
 *
 * This exception serves as the base class for custom exceptions in the application.
 *
 * @author Gleb Nickolaenko
 */
public class BaseException extends Exception {
    /**
     * Constructs a new BaseException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public BaseException(String message) {
        super(message);
    }
}
