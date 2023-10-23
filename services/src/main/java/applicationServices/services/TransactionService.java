package applicationServices.services;


import applicationServices.exceptions.BaseException;
import model.Transaction;

import java.util.List;

/**
 * An interface representing a service for managing financial transactions.
 *
 * This service provides methods for saving, retrieving, and managing financial transactions.
 *
 * @author Gleb Nickolaenko
 */
public interface TransactionService {
    /**
     * Saves a financial transaction in the repository.
     *
     * @param transaction The financial transaction to be saved.
     * @return The saved financial transaction.
     */
    Transaction save(Transaction transaction)  throws BaseException;



    /**
     * Retrieves a financial transaction by its unique ID.
     *
     * @param id The unique ID of the financial transaction to retrieve.
     * @return The financial transaction with the specified ID, or null if not found.
     */
    List<Transaction> getAllByBankAccId(Long id) throws BaseException;
}
