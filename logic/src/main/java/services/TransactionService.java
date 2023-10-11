package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.transaction.TransactionDontExistException;
import applicationServices.exceptions.transaction.TransactionNotUniqIDException;
import applicationServices.services.TransactionServiceI;
import model.Transaction;
import modelRepositoriesI.TransactionRepositoryI;

import java.util.List;

/**
 * A service class for managing financial transactions and related operations.
 *
 * This service class provides methods for creating, retrieving, and managing financial transactions.
 *
 * @author Gleb Nickolaenko
 */
public class TransactionService implements TransactionServiceI {

    private final TransactionRepositoryI transactionRepository;

    /**
     * Constructs a new TransactionService with the specified transaction repository.
     *
     * @param transactionRepository The repository for managing financial transactions.
     */
    public TransactionService(TransactionRepositoryI transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Saves a new transaction in the system.
     *
     * @param transaction The transaction to save.
     * @return The saved transaction.
     * @throws BaseException if a transaction with the same ID already exists.
     */
    @Override
    public Transaction save(Transaction transaction) throws BaseException {
        if (transactionRepository.getById(transaction.getId()) != null) {
            throw new TransactionNotUniqIDException("A transaction with the same ID already exists");
        }
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves a list of all financial transactions in the system.
     *
     * @return A list of all financial transactions.
     * @throws BaseException if an error occurs while retrieving the transactions.
     */
    @Override
    public List<Transaction> getAll() throws BaseException {
        return transactionRepository.getAll();
    }

    /**
     * Retrieves a specific financial transaction by its ID.
     *
     * @param id The ID of the transaction to retrieve.
     * @return The transaction with the specified ID.
     * @throws BaseException if the transaction with the specified ID doesn't exist.
     */
    @Override
    public Transaction getById(String id) throws BaseException {
        Transaction transaction = transactionRepository.getById(id);
        if (transaction == null) {
            throw new TransactionDontExistException("Transaction with ID: " + id + " does not exist");
        }
        return transaction;
    }
}
