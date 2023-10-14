package applicationServices.exceptions.transaction;

import applicationServices.exceptions.BaseException;

/**
 * Exception for non-existing transactions.
 *
 * @author Gleb Nickolaenko
 */
public class TransactionDontExistException extends BaseException {
    public TransactionDontExistException(String message) {
        super(message);
    }
}
