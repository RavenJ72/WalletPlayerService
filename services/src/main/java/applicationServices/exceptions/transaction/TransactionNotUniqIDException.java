package applicationServices.exceptions.transaction;

import applicationServices.exceptions.BaseException;

/**
 * Exception for non-unique transaction IDs.
 *
 * @author Gleb Nickolaenko
 */
public class TransactionNotUniqIDException extends BaseException {
    public TransactionNotUniqIDException(String message) {
        super(message);
    }
}
