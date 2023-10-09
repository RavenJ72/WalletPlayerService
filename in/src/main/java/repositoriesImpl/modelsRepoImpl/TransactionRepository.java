package repositoriesImpl.modelsRepoImpl;


import model.Transaction;
import modelRepositoriesI.TransactionRepositoryI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository implements TransactionRepositoryI {


    private final Map<String, Transaction> memory = new HashMap<>();


    @Override
    public Transaction save(Transaction transaction) {
        return memory.put(transaction.getId(),transaction);
    }

    public List<Transaction> getAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public Transaction getById(String id) {
        return memory.get(id);
    }
}

