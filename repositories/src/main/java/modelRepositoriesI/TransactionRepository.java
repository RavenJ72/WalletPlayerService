package modelRepositoriesI;

import model.Transaction;

import java.util.List;

/**
 * An interface representing a repository for managing financial transactions.
 *
 * This repository provides methods for saving, retrieving, and managing financial transactions.
 *
 * @author Gleb Nickolaenko
 */
public interface TransactionRepository {
    /**
     * Saves a financial transaction in the repository.
     *
     * @param transaction The financial transaction to be saved.
     * @return The saved financial transaction.
     */
    Transaction save(Transaction transaction);

    /**
     * Retrieves a list of all financial transactions by player id in the repository.
     *
     * @return A list of all financial transactions.
     */
    List<Transaction> getAllById(Long id);


}
