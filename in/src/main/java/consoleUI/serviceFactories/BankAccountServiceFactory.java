package consoleUI.serviceFactories;

import applicationServices.services.BankAccountServiceI;
import modelRepositoriesI.BankAccountRepositoryI;
import repositoriesImpl.repoFactories.BankAccountRepositoryFactory;
import services.BankAccountService;

public final class BankAccountServiceFactory {

    private static BankAccountServiceI bankAccountServiceInstance;

    public static BankAccountServiceI getBankAccountService(BankAccountRepositoryI bankAccountRepository) {
        if (bankAccountServiceInstance == null) {
            bankAccountServiceInstance = new BankAccountService(BankAccountRepositoryFactory.getBankAccountRepository());
        }
        return bankAccountServiceInstance;
    }
}

