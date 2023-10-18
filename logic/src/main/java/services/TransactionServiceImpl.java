package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.exceptions.transaction.TransactionDontExistException;
import applicationServices.exceptions.transaction.TransactionNotUniqIDException;
import applicationServices.services.TransactionService;
import model.Transaction;
import modelRepositoriesI.TransactionRepository;

import java.util.List;

/**
 * A service class for managing financial transactions and related operations.
 *
 * This service class encapsulates the logic for creating, retrieving, and managing financial transactions.
 *
 * @author Gleb Nickolaenko
 */
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    /**
     * Constructs a new TransactionService with the specified repository.
     *
     * @param transactionRepository The repository for managing transaction information.
     */
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Saves a transaction in the system.
     *
     * @param transaction The transaction to be saved.
     * @return The saved transaction.
     * @throws TransactionNotUniqIDException If a record with the specified ID already exists.
     */
    @Override
    public Transaction save(Transaction transaction)  throws BaseException {
        Transaction transactionJBDC = transactionRepository.save(transaction);
        if(transactionJBDC == null){
            throw new TransactionNotUniqIDException("A record with such an ID already exists");
        }
        return transactionJBDC;
    }

    /**
     * Retrieves all transactions associated with a specified bank account.
     *
     * @param id The ID of the bank account to retrieve transactions for.
     * @return A list of transactions associated with the specified bank account.
     * @throws BankAccountNotFoundException If there is no account with the specified ID.
     */
    @Override
    public List<Transaction> getAllByBankAccId(Long id) throws BaseException{
        List<Transaction> transactions = transactionRepository.getAllById(id);
        if(transactions == null){
            throw new BankAccountNotFoundException("There is no account with such ID");
        }
        return transactions;
    }

}
