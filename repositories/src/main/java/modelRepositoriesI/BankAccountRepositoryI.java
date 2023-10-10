package modelRepositoriesI;

import model.BankAccount;
import model.Transaction;

public interface BankAccountRepositoryI {
    BankAccount save(BankAccount bankAccount);
    BankAccount findById(String id);

}
