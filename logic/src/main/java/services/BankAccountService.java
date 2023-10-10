package services;


import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotEnoughMoney;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.exceptions.transaction.TransactionNotUniqIDException;
import applicationServices.services.BankAccountServiceI;
import applicationServices.services.TransactionServiceI;
import model.BankAccount;
import model.Transaction;
import modelRepositoriesI.BankAccountRepositoryI;

import java.math.BigDecimal;

public class BankAccountService implements BankAccountServiceI {
    private final BankAccountRepositoryI bankAccountRepository;
    private final TransactionServiceI transactionServiceI;

    public BankAccountService(BankAccountRepositoryI bankAccountRepository, TransactionServiceI transactionServiceI) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionServiceI = transactionServiceI;
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount findAccountById(String id) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(id);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Issued account dont exists");
        }
        return bankAccount;
    }

    @Override
    public boolean withdrawMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Issued account dont exists");
        }

        Transaction transaction = new Transaction(Transaction.TransactionType.DEBIT.toString(),amount,bankAccountId);

        if(transactionId != null){
            transaction.setId(transactionId);
        }

        if(bankAccount.getBalance().intValue() - amount.intValue() < 0){
            throw new BankAccountNotEnoughMoney("There are not enough funds for withdrawal");
        }
        try {
            transactionServiceI.save(transaction);
        }catch (BaseException e){
            System.out.println(e.getMessage());
            return false;
        }
        bankAccount.getPlayerTransactions().add(transaction);
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));

        return true;
    }

    @Override
    public boolean depositMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Issued account dont exists");
        }

        Transaction transaction = new Transaction(Transaction.TransactionType.CREDIT.toString(),amount,bankAccountId);

        if(transactionId != null){
            transaction.setId(transactionId);
        }

        try {
            transactionServiceI.save(transaction);
        }catch (BaseException e){
            System.out.println(e.getMessage());
            return false;
        }
        bankAccount.getPlayerTransactions().add(transaction);
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        return true;
    }



}
