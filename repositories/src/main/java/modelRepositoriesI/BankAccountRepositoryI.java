package modelRepositoriesI;

import model.BankAccount;

public interface BankAccountRepositoryI {
    BankAccount save(BankAccount bankAccount);
    BankAccount findById(String id);
}
