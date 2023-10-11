package repositoriesImpl.repoFactories;

import modelRepositoriesI.TransactionRepositoryI;
import repositoriesImpl.modelsRepoImpl.TransactionRepository;

/**
 * A factory class responsible for providing a single instance of the TransactionRepository.
 *
 * This class ensures that only one instance of the TransactionRepository is created and reused for efficiency.
 * It provides access to the repository for transaction-related operations.
 * @author Gleb Nickolaenko
 */
public final class TransactionRepoFactory {

    private static TransactionRepositoryI transactionRepositoryInstance;

    /**
     * Retrieves the single instance of the TransactionRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the TransactionRepository.
     */
    public static TransactionRepositoryI getTransactionRepository() {
        if (transactionRepositoryInstance == null) {
            transactionRepositoryInstance = new TransactionRepository();
        }
        return transactionRepositoryInstance;
    }
}
