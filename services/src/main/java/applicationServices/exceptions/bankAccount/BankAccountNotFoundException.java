package applicationServices.exceptions.bankAccount;

import applicationServices.exceptions.BaseException;
import model.BankAccount;

public class BankAccountNotFoundException extends BaseException {

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
