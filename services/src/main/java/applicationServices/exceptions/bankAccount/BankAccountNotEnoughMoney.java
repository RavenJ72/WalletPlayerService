package applicationServices.exceptions.bankAccount;

import applicationServices.exceptions.BaseException;

/**
 * Exception for insufficient funds in a bank account.
 *
 * @author Gleb Nickolaenko
 */
public class BankAccountNotEnoughMoney extends BaseException {
    public BankAccountNotEnoughMoney(String message) {
        super(message);
    }
}
