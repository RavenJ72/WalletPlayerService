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
 * <p>
 * This service class provides methods for creating, retrieving, and managing bank accounts,
 * as well as performing financial transactions such as withdrawals and deposits.
 * </p>
 *
 * @author Gleb Nickolaenko
 */
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionService transactionService;

    /**
     * Constructs a new {@code BankAccountServiceImpl} with the specified bank account repository
     * and transaction service.
     *
     * @param bankAccountRepository the repository for managing bank accounts.
     * @param transactionService the service for managing transactions.
     */
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, TransactionService transactionService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionService = transactionService;
    }

    /**
     * Saves a bank account to the repository.
     *
     * @param bankAccount the bank account to be saved.
     * @return the saved bank account.
     */
    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Finds a bank account by its unique ID.
     * <p>
     * If no bank account with the specified ID is found, a {@code BankAccountNotFoundException} is thrown.
     * </p>
     *
     * @param id the unique ID of the bank account to find.
     * @return the found bank account.
     * @throws BankAccountNotFoundException if no bank account with the specified ID is found.
     */
    @Override
    public BankAccount findAccountById(Long id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("There is no account with such ids");
        }
        return bankAccount;
    }

}
