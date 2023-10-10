package services;


import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.services.BankAccountServiceI;
import applicationServices.services.TransactionServiceI;
import model.BankAccount;
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
        if(bankAccount.getBalance().intValue() - amount.intValue() >= 0){

        }
        return false;
    }

    @Override
    public boolean depositMoney(String bankAccountId, BigDecimal amount,String transactionId) throws BaseException {
        return false;
    }



}
