package applicationServices.exceptions.transaction;

import applicationServices.exceptions.BaseException;

public class TransactionDontExistException extends BaseException {
    public TransactionDontExistException(String message) {
        super(message);
    }
}
