import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotEnoughMoney;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.services.TransactionService;
import model.BankAccount;
import model.Transaction;
import modelRepositoriesI.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BankAccountServiceImpl;

import java.math.BigDecimal;

public class BankAccountServiceImplTest {
    private BankAccountServiceImpl bankAccountServiceImpl;
    private BankAccountRepository bankAccountRepository;
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        bankAccountRepository = mock(BankAccountRepository.class);
        transactionService = mock(TransactionService.class);
        bankAccountServiceImpl = new BankAccountServiceImpl(bankAccountRepository, transactionService);
    }

    @Test
    public void testWithdrawMoney_Success() throws BaseException {
        // Создаем экземпляр BankAccount и Transaction
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId("testId");

        Transaction transaction = new Transaction("debit", BigDecimal.valueOf(50), "testId");
        transaction.setId("testId");

        // Устанавливаем баланс на счете
        bankAccount.setBalance(BigDecimal.valueOf(100));

        // Устанавливаем ожидаемое поведение для мок-объектов
        when(bankAccountRepository.findById("testId")).thenReturn(bankAccount);
        when(transactionService.save(transaction)).thenReturn(transaction);

        // Вызываем метод withdrawMoney из bankAccountService
        boolean result = bankAccountServiceImpl.withdrawMoney("testId", BigDecimal.valueOf(50), "testId");

        // Проверяем, что операция выполнена успешно
        assertThat(result).isTrue();
        // Проверяем, что баланс уменьшился на 50
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(50));
        // Проверяем, что транзакция была добавлена к аккаунту
        assertThat(bankAccount.getPlayerTransactions()).isNotNull();
    }

    @Test
    public void testDepositMoney_Success() throws BaseException {
        BankAccount bankAccount = new BankAccount();
        Transaction transaction = new Transaction("credit", BigDecimal.valueOf(50), "testId");

        bankAccount.setBalance(BigDecimal.valueOf(100));

        when(bankAccountRepository.findById("testId")).thenReturn(bankAccount);
        when(transactionService.save(transaction)).thenReturn(transaction);

        boolean result = bankAccountServiceImpl.depositMoney("testId", BigDecimal.valueOf(50), null);

        assertThat(result).isTrue();
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(150));
        assertThat(bankAccount.getPlayerTransactions()).isNotNull();
    }

    @Test
    public void testFindAccountById_Success() throws BaseException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId("testId");
        bankAccount.setBalance(BigDecimal.valueOf(100));

        when(bankAccountRepository.findById("testId")).thenReturn(bankAccount);

        BankAccount foundAccount = bankAccountServiceImpl.findAccountById("testId");

        assertThat(foundAccount).isEqualTo(bankAccount);
    }

    @Test
    public void testFindAccountByIdNotFoundException() throws BaseException {
        when(bankAccountRepository.findById("testId")).thenReturn(null);

        assertThatThrownBy(() -> bankAccountServiceImpl.findAccountById("testId"))
                .isInstanceOf(BankAccountNotFoundException.class)
                .hasMessage("The requested account doesn't exist");
    }

    @Test
    public void testSave_Success() {
        BankAccount bankAccount = new BankAccount();

        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);

        BankAccount savedAccount = bankAccountServiceImpl.save(bankAccount);

        assertThat(savedAccount).isEqualTo(bankAccount);
    }

    @Test
    public void testWithdrawMoneyNotEnoughMoneyException() throws BaseException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(BigDecimal.valueOf(100));

        when(bankAccountRepository.findById("testId")).thenReturn(bankAccount);

        // Попытка снять больше денег, чем есть на счете
        BigDecimal withdrawalAmount = BigDecimal.valueOf(150);

        assertThatThrownBy(() -> bankAccountServiceImpl.withdrawMoney("testId", withdrawalAmount, null))
                .isInstanceOf(BankAccountNotEnoughMoney.class)
                .hasMessage("There are not enough funds for withdrawal");
    }



}
