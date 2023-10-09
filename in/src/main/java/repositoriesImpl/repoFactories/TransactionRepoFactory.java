package repositoriesImpl.repoFactories;

import modelRepositoriesI.TransactionRepositoryI;
import repositoriesImpl.modelsRepoImpl.TransactionRepository;

public final class TransactionRepoFactory {

    private static TransactionRepositoryI transactionRepositoryInstance;

    public static TransactionRepositoryI getTransactionRepository() {
        if (transactionRepositoryInstance == null) {
            transactionRepositoryInstance = new TransactionRepository();
        }
        return transactionRepositoryInstance;
    }
}
