package applicationServices.services;
import applicationServices.exceptions.BaseException;
import model.BankAccount;

import java.math.BigDecimal;

/**
 * An interface representing a service for managing bank accounts.
 *
 * This service provides methods for saving, retrieving, and managing bank accounts. It also supports operations such as
 * depositing and withdrawing money from bank accounts.
 *
 * @author Gleb Nickolaenko
 */
public interface BankAccountService {
    /**
     * Saves a bank account in the repository.
     *
     * @param bankAccount The bank account to be saved.
     * @return The saved bank account.
     */
    BankAccount save(BankAccount bankAccount);

    /**
     * Retrieves a bank account by its unique ID.
     *
     * @param id The unique ID of the bank account to retrieve.
     * @return The found bank account, or null if not found.
     */
    BankAccount findAccountById(Long id) throws BaseException;

}
