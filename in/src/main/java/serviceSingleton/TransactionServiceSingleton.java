package serviceSingleton;

import applicationServices.services.TransactionService;
import repoSingleton.TransactionRepoSingleton;
import services.TransactionServiceImpl;

/**
 * A singleton class responsible for providing a single instance of the TransactionService.
 *
 * This class ensures that only one instance of the TransactionService is created and reused for efficiency.
 * It retrieves the necessary repository to initialize the TransactionService.
 *
 * Usage:
 * To obtain the single instance of the TransactionService, use the getTransactionService() method.
 *
 * Example:
 * TransactionServiceI transactionService = TransactionServiceSingleton.getTransactionService();
 *
 * @author Gleb Nickolaenko
 */
public final class TransactionServiceSingleton {

    private static TransactionService transactionServiceInstance;

    /**
     * Retrieves the single instance of the TransactionService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the TransactionService.
     */
    public static TransactionService getTransactionService() {
        if (transactionServiceInstance == null) {
            transactionServiceInstance = new TransactionServiceImpl(TransactionRepoSingleton.getTransactionRepository());
        }
        return transactionServiceInstance;
    }
}

