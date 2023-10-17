package services;
import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.services.*;

import model.BankAccount;

import modelRepositoriesI.BankAccountRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A service class for managing bank accounts and related financial transactions.
 *
 * This service class provides methods for creating, retrieving, and managing bank accounts,
 * as well as performing financial transactions such as withdrawals and deposits.
 *
 * @author Gleb Nickolaenko
 */
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionService transactionService;


    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, TransactionService transactionService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionService = transactionService;
    }


    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }


    @Override
    public BankAccount findAccountById(Long id) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(id);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("There is no account with such ids");
        }
        return bankAccount;
    }

}