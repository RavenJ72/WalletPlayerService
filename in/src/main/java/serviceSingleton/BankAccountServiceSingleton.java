package serviceSingleton;

import applicationServices.services.BankAccountService;
import repoSingleton.BankAccountRepositorySingleton;
import services.BankAccountServiceImpl;

/**
 * A singleton class responsible for providing a single instance of the BankAccountService.
 *
 * This class ensures that only one instance of the BankAccountService is created and reused for efficiency.
 * It retrieves the necessary repository and transaction services to initialize the BankAccountService.
 *
 * Usage:
 * To obtain the single instance of the BankAccountService, use the getBankAccountService() method.
 *
 * Example:
 * BankAccountServiceI bankAccountService = BankAccountServiceSingleton.getBankAccountService();
 *
 * @author Gleb Nickolaenko
 */
public final class BankAccountServiceSingleton {

    private static BankAccountService bankAccountServiceInstance;

    /**
     * Retrieves the single instance of the BankAccountService. If the instance does not exist, it is created and initialized.
     *
     * @return The single instance of the BankAccountService.
     */
    public static BankAccountService getBankAccountService() {
        if (bankAccountServiceInstance == null) {
            bankAccountServiceInstance = new BankAccountServiceImpl(
                    BankAccountRepositorySingleton.getBankAccountRepository(),
                    TransactionServiceSingleton.getTransactionService()
            );
        }
        return bankAccountServiceInstance;
    }
}
