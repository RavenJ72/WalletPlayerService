package repositoriesImpl.repoFactories;

import modelRepositoriesI.BankAccountRepositoryI;
import repositoriesImpl.modelsRepoImpl.BankAccountRepository;

public final class BankAccountRepositoryFactory {

    private static BankAccountRepositoryI bankAccountRepositoryInstance;

    public static BankAccountRepositoryI getBankAccountRepository() {
        if (bankAccountRepositoryInstance == null) {
            bankAccountRepositoryInstance = new BankAccountRepository();
        }
        return bankAccountRepositoryInstance;
    }
}
