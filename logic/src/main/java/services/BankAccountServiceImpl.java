package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotEnoughMoney;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.services.BankAccountService;
import applicationServices.services.TransactionService;
import model.BankAccount;
import model.Transaction;
import modelRepositoriesI.BankAccountRepository;

import java.math.BigDecimal;

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

    /**
     * Constructs a new BankAccountService with the specified repositories and services.
     *
     * @param bankAccountRepository The repository for managing bank accounts.
     * @param transactionService The service for managing financial transactions.
     */
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, TransactionService transactionService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionService = transactionService;
    }

    /**
     * Creates or updates a bank account in the system.
     *
     * @param bankAccount The bank account to save or update.
     * @return The saved or updated bank account.
     */
    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Retrieves a bank account by its unique ID.
     *
     * @param id The unique ID of the bank account to retrieve.
     * @return The bank account with the specified ID.
     * @throws BaseException if the bank account doesn't exist.
     */
    @Override
    public BankAccount findAccountById(String id) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(id);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("The requested account doesn't exist");
        }
        return bankAccount;
    }

    /**
     * Performs a withdrawal operation from a bank account.
     *
     * @param bankAccountId The ID of the bank account from which to withdraw.
     * @param amount The amount to withdraw.
     * @param transactionId The ID of the transaction (optional).
     * @return true if the withdrawal is successful, false otherwise.
     * @throws BaseException if the operation encounters an error, such as a non-existing account or insufficient funds.
     */
    @Override
    public boolean withdrawMoney(String bankAccountId, BigDecimal amount, String transactionId) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("The specified account doesn't exist");
        }

        Transaction transaction = new Transaction(Transaction.TransactionType.DEBIT.toString(), amount, bankAccountId);

        if(transactionId != null){
            transaction.setId(transactionId);
        }

        if(bankAccount.getBalance().intValue() - amount.intValue() < 0){
            throw new BankAccountNotEnoughMoney("There are not enough funds for withdrawal");
        }
        try {
            transactionService.save(transaction);
        } catch (BaseException e){
            System.out.println(e.getMessage());
            return false;
        }
        bankAccount.getPlayerTransactions().add(transaction);
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));

        return true;
    }

    /**
     * Performs a deposit operation into a bank account.
     *
     * @param bankAccountId The ID of the bank account to which to deposit.
     * @param amount The amount to deposit.
     * @param transactionId The ID of the transaction (optional).
     * @return true if the deposit is successful, false otherwise.
     * @throws BaseException if the operation encounters an error, such as a non-existing account.
     */
    @Override
    public boolean depositMoney(String bankAccountId, BigDecimal amount, String transactionId) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("The specified account doesn't exist");
        }

        Transaction transaction = new Transaction(Transaction.TransactionType.CREDIT.toString(), amount, bankAccountId);

        if(transactionId != null){
            transaction.setId(transactionId);
        }

        try {
            transactionService.save(transaction);
        } catch (BaseException e){
            System.out.println(e.getMessage());
            return false;
        }
        bankAccount.getPlayerTransactions().add(transaction);
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        return true;
    }
}
