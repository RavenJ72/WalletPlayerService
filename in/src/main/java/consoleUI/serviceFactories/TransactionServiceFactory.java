package consoleUI.serviceFactories;

import applicationServices.services.TransactionServiceI;
import modelRepositoriesI.TransactionRepositoryI;
import repositoriesImpl.repoFactories.TransactionRepoFactory;
import services.TransactionService;

public final class TransactionServiceFactory {

    private static TransactionServiceI transactionServiceInstance;

    public static TransactionServiceI getTransactionService(TransactionRepositoryI transactionRepository) {
        if (transactionServiceInstance == null) {
            transactionServiceInstance = new TransactionService(TransactionRepoFactory.getTransactionRepository());
        }
        return transactionServiceInstance;
    }
}

