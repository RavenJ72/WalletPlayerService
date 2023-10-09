package applicationServices.services;

import applicationServices.exceptions.BaseException;
import model.Transaction;

import java.util.List;

public interface TransactionServiceI {
   Transaction save(Transaction transaction) throws BaseException;
    List<Transaction> getAll() throws BaseException;
    Transaction getById(String id) throws BaseException;

}
