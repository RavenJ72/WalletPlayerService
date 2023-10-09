package modelRepositoriesI;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface TransactionRepositoryI {

    Transaction save(Transaction transaction);
    List<Transaction> getAll();
    Transaction getById(String id);
}
