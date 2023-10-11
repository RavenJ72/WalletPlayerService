package repositoriesImpl.modelsRepoImpl;

import model.BankAccount;
import modelRepositoriesI.BankAccountRepositoryI;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the BankAccountRepositoryI interface for managing BankAccount objects in memory.
 *
 * This class provides methods for storing and retrieving BankAccount objects.
 * @author Gleb Nickolaenko
 */
public class BankAccountRepository implements BankAccountRepositoryI {

    private final Map<String, BankAccount> memory = new HashMap<>();

    /**
     * Saves a BankAccount object to the repository.
     *
     * @param bankAccount The BankAccount object to be saved.
     * @return The saved BankAccount object.
     */
    @Override
    public BankAccount save(BankAccount bankAccount) {
        return memory.put(bankAccount.getId(), bankAccount);
    }

    /**
     * Retrieves a BankAccount object by its unique identifier.
     *
     * @param id The unique identifier of the BankAccount to retrieve.
     * @return The BankAccount object associated with the given identifier, or null if not found.
     */
    @Override
    public BankAccount findById(String id) {
        return memory.get(id);
    }
}
