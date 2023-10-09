package services;

import applicationServices.exceptions.BaseException;
import applicationServices.services.TransactionServiceI;
import model.Transaction;
import modelRepositoriesI.TransactionRepositoryI;

import java.util.List;

public class TransactionService implements TransactionServiceI {

    private final TransactionRepositoryI transactionRepository;

    public TransactionService(TransactionRepositoryI transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) throws BaseException {
        return null;
    }

    @Override
    public List<Transaction> getAll() throws BaseException {
        return null;
    }

    @Override
    public Transaction getById(String id) throws BaseException {
        return null;
    }
}
