package applicationServices.exceptions.transaction;

import applicationServices.exceptions.BaseException;

public class TransactionNotUniqIDException extends BaseException {
    public TransactionNotUniqIDException(String message) {
        super(message);
    }
}
