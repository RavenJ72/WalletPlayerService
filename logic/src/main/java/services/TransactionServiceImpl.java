package services;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.exceptions.transaction.TransactionDontExistException;
import applicationServices.exceptions.transaction.TransactionNotUniqIDException;
import applicationServices.services.TransactionService;
import model.Transaction;
import modelRepositoriesI.TransactionRepository;

import java.util.List;

/**
 * A service class for managing financial transactions and related operations.
 *
 * This service class provides methods for creating, retrieving, and managing financial transactions.
 *
 * @author Gleb Nickolaenko
 */
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction save(Transaction transaction)  throws BaseException {
        Transaction transactionJBDC = transactionRepository.save(transaction);
        if(transactionJBDC == null){
            throw new TransactionNotUniqIDException("A record with such an ID already exists");
        }
        return transactionJBDC;
    }

    @Override
    public List<Transaction> getAllByBankAccId(Long id) throws BaseException{
        List<Transaction> transactions = transactionRepository.getAllById(id);
        if(transactions == null){
            throw new BankAccountNotFoundException("There is no account with such ids");
        }
        return transactions;
    }


}
