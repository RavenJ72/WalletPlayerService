package applicationServices.exceptions.bankAccount;

import applicationServices.exceptions.BaseException;

public class BankAccountNotEnoughMoney extends BaseException {
    public BankAccountNotEnoughMoney(String message) {
        super(message);
    }
}
