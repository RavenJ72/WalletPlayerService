package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.BankAccount;

public interface BankAccountServiceI {
    BankAccount save(BankAccount bankAccount) throws BaseException;
    BankAccount findAccountById(String id) throws BaseException;


}
