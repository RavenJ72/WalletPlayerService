package serviceFactories;

import applicationServices.services.TransactionServiceI;
import repositoriesImpl.repoFactories.TransactionRepoFactory;
import services.TransactionService;

/**
 * A factory class responsible for providing a single instance of the TransactionService.
 *
 * This class ensures that only one instance of the TransactionService is created and reused for efficiency.
 * It retrieves the necessary repository to initialize the TransactionService.
 * @author Gleb Nickolaenko
 */
public final class TransactionServiceFactory {

    private static TransactionServiceI transactionServiceInstance;

    /**
     * Retrieves the single instance of the TransactionService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the TransactionService.
     */
    public static TransactionServiceI getTransactionService() {
        if (transactionServiceInstance == null) {
            transactionServiceInstance = new TransactionService(TransactionRepoFactory.getTransactionRepository());
        }
        return transactionServiceInstance;
    }
}
