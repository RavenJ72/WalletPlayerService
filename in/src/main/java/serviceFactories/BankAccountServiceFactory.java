package serviceFactories;

import applicationServices.services.BankAccountServiceI;
import repositoriesImpl.repoFactories.BankAccountRepositoryFactory;
import services.BankAccountService;

/**
 * A factory class responsible for providing a single instance of the BankAccountService.
 *
 * This class ensures that only one instance of the BankAccountService is created and reused for efficiency.
 * It retrieves the necessary repository and transaction services to initialize the BankAccountService.
 * @author Gleb Nickolaenko
 */
public final class BankAccountServiceFactory {

    private static BankAccountServiceI bankAccountServiceInstance;

    /**
     * Retrieves the single instance of the BankAccountService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the BankAccountService.
     */
    public static BankAccountServiceI getBankAccountService() {
        if (bankAccountServiceInstance == null) {
            bankAccountServiceInstance = new BankAccountService(
                    BankAccountRepositoryFactory.getBankAccountRepository(),
                    TransactionServiceFactory.getTransactionService()
            );
        }
        return bankAccountServiceInstance;
    }
}
