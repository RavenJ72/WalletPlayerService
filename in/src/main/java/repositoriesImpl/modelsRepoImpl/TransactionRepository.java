package repositoriesImpl.modelsRepoImpl;

import model.Transaction;
import modelRepositoriesI.TransactionRepositoryI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the TransactionRepositoryI interface for managing Transaction objects in memory.
 *
 * This class provides methods for storing, retrieving, and managing Transaction objects.
 * @author Gleb Nickolaenko
 */
public class TransactionRepository implements TransactionRepositoryI {

    private final Map<String, Transaction> memory = new HashMap<>();

    /**
     * Saves a Transaction object to the repository.
     *
     * @param transaction The Transaction object to be saved.
     * @return The saved Transaction object.
     */
    @Override
    public Transaction save(Transaction transaction) {
        return memory.put(transaction.getId(), transaction);
    }

    /**
     * Retrieves a list of all Transaction objects in the repository.
     *
     * @return A list of all Transaction objects stored in the repository.
     */
    public List<Transaction> getAll() {
        return new ArrayList<>(memory.values());
    }

    /**
     * Retrieves a Transaction object by its unique identifier.
     *
     * @param id The unique identifier of the Transaction to retrieve.
     * @return The Transaction object associated with the given identifier, or null if not found.
     */
    @Override
    public Transaction getById(String id) {
        return memory.get(id);
    }
}
