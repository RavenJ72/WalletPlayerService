package repositoriesImpl.repoFactories;

import modelRepositoriesI.BankAccountRepositoryI;
import repositoriesImpl.modelsRepoImpl.BankAccountRepository;

/**
 * A factory class responsible for providing a single instance of the BankAccountRepository.
 *
 * This class ensures that only one instance of the BankAccountRepository is created and reused for efficiency.
 * It provides access to the repository for bank account-related operations.
 * @author Gleb Nickolaenko
 */
public final class BankAccountRepositoryFactory {

    private static BankAccountRepositoryI bankAccountRepositoryInstance;

    /**
     * Retrieves the single instance of the BankAccountRepository. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the BankAccountRepository.
     */
    public static BankAccountRepositoryI getBankAccountRepository() {
        if (bankAccountRepositoryInstance == null) {
            bankAccountRepositoryInstance = new BankAccountRepository();
        }
        return bankAccountRepositoryInstance;
    }
}
