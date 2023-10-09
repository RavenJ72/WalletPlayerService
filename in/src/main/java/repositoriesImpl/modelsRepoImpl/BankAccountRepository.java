package repositoriesImpl.modelsRepoImpl;

import model.BankAccount;

import modelRepositoriesI.BankAccountRepositoryI;

import java.util.HashMap;
import java.util.Map;

public class BankAccountRepository implements BankAccountRepositoryI {

    private final Map<String, BankAccount> memory = new HashMap<>();

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return memory.put(bankAccount.getId(),bankAccount);
    }

    @Override
    public BankAccount findById(String id) {
        return memory.get(id);
    }
}
