package repositoriesImpl.repoFactories;

import modelRepositoriesI.TransactionRepository;
import repositoriesImpl.modelsRepoImpl.TransactionRepositoryImpl;

/**
 * A singleton class responsible for providing a single instance of the TransactionRepository.
 *
 * This class ensures that only one instance of the TransactionRepository is created and reused for efficiency.
 * It provides access to the repository for transaction-related operations.
 *
 * Usage:
 * To obtain the single instance of the TransactionRepository, use the getTransactionRepository() method.
 *
 * Example:
 * TransactionRepositoryI transactionRepository = TransactionRepoSingleton.getTransactionRepository();
 *
 * @author Gleb Nickolaenko
 */
public final class TransactionRepoSingleton {

    private static TransactionRepository transactionRepositoryInstance;

    /**
     * Retrieves the single instance of the TransactionRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the TransactionRepository.
     */
    public static TransactionRepository getTransactionRepository() {
        if (transactionRepositoryInstance == null) {
            transactionRepositoryInstance = new TransactionRepositoryImpl();
        }
        return transactionRepositoryInstance;
    }
}
