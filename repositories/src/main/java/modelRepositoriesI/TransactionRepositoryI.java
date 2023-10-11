package modelRepositoriesI;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface representing a repository for managing financial transactions.
 *
 * This repository provides methods for saving, retrieving, and managing financial transactions.
 *
 * @author Gleb Nickolaenko
 */
public interface TransactionRepositoryI {
    /**
     * Saves a financial transaction in the repository.
     *
     * @param transaction The financial transaction to be saved.
     * @return The saved financial transaction.
     */
    Transaction save(Transaction transaction);

    /**
     * Retrieves a list of all financial transactions in the repository.
     *
     * @return A list of all financial transactions.
     */
    List<Transaction> getAll();

    /**
     * Retrieves a financial transaction by its unique ID.
     *
     * @param id The unique ID of the financial transaction to retrieve.
     * @return The financial transaction with the specified ID, or null if not found.
     */
    Transaction getById(String id);
}
