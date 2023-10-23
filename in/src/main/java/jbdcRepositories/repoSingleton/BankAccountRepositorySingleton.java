package jbdcRepositories.repoSingleton;

import jbdcRepositories.BankAccountRepositoryImpl;
import jbdcRepositories.connection.DatabaseManager;
import modelRepositoriesI.BankAccountRepository;


/**
 * A singleton class responsible for providing a single instance of the BankAccountRepository.
 *
 * This class ensures that only one instance of the BankAccountRepository is created and reused for efficiency.
 * It provides access to the repository for bank account-related operations.
 *
 * Usage:
 * To obtain the single instance of the BankAccountRepository, use the getBankAccountRepository() method.
 *
 * Example:
 * BankAccountRepositoryI bankAccountRepository = BankAccountRepositorySingleton.getBankAccountRepository();
 *
 * @author Gleb Nickolaenko
 */
public final class BankAccountRepositorySingleton {

    private static BankAccountRepository bankAccountRepositoryInstance;

    /**
     * Retrieves the single instance of the BankAccountRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the BankAccountRepository.
     */
    public static BankAccountRepository getBankAccountRepository() {
        if (bankAccountRepositoryInstance == null) {
            bankAccountRepositoryInstance = new BankAccountRepositoryImpl(TransactionRepoSingleton.getTransactionRepository(), DatabaseManager.getUrl());
        }
        return bankAccountRepositoryInstance;
    }
}
