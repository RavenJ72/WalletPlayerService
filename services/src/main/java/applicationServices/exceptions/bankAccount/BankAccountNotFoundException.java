package applicationServices.exceptions.bankAccount;

import applicationServices.exceptions.BaseException;

/**
 * Exception for non-existing bank accounts.
 *
 * @author Gleb Nickolaenko
 */
public class BankAccountNotFoundException extends BaseException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
