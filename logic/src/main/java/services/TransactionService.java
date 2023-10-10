package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.player.PlayerDontExistException;
import applicationServices.exceptions.transaction.TransactionDontExistException;
import applicationServices.exceptions.transaction.TransactionNotUniqIDException;
import applicationServices.services.TransactionServiceI;
import model.Player;
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
        if(transactionRepository.getById(transaction.getId()) != null) {
            throw new TransactionNotUniqIDException("Not a unique transaction ID");
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAll() throws BaseException {
        return transactionRepository.getAll();
    }

    @Override
    public Transaction getById(String id) throws BaseException {
        Transaction transaction = transactionRepository.getById(id);
        if(transaction == null){
            throw new TransactionDontExistException("Transaction with id: " + id + " does not exist");
        }
        return transaction;
    }
}
