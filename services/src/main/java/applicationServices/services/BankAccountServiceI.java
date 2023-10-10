package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.BankAccount;
import model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountServiceI {
    BankAccount save(BankAccount bankAccount) throws BaseException;
    BankAccount findAccountById(String id) throws BaseException;

    boolean withdrawMoney(String bankAccountId, BigDecimal amountString ,String transactionId) throws BaseException;

    boolean depositMoney(String bankAccountId, BigDecimal amountString ,String transactionId) throws BaseException;




}
