package services;


import applicationServices.exceptions.BaseException;
import applicationServices.services.BankAccountServiceI;
import model.BankAccount;
import modelRepositoriesI.BankAccountRepositoryI;

public class BankAccountService implements BankAccountServiceI {
    private final BankAccountRepositoryI bankAccountRepository;

    public BankAccountService(BankAccountRepositoryI bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount save(BankAccount bankAccount) throws BaseException {
        return null;
    }

    @Override
    public BankAccount findAccountById(String id) throws BaseException {
        return null;
    }
}
