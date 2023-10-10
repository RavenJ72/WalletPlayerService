package consoleUI.serviceFactories;

import applicationServices.services.BankAccountServiceI;
import repositoriesImpl.repoFactories.BankAccountRepositoryFactory;
import services.BankAccountService;

public final class BankAccountServiceFactory {

    private static BankAccountServiceI bankAccountServiceInstance;

    public static BankAccountServiceI getBankAccountService() {
        if (bankAccountServiceInstance == null) {
            bankAccountServiceInstance = new BankAccountService(BankAccountRepositoryFactory.getBankAccountRepository(), TransactionServiceFactory.getTransactionService());
        }
        return bankAccountServiceInstance;
    }
}

