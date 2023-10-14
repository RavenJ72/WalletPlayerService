import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.transaction.TransactionDontExistException;
import applicationServices.exceptions.transaction.TransactionNotUniqIDException;
import model.Transaction;
import modelRepositoriesI.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TransactionServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImplTest {
    private TransactionServiceImpl transactionServiceImpl;
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        // Создаем мок-объект TransactionRepositoryI
        transactionRepository = mock(TransactionRepository.class);
        transactionServiceImpl = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void testSaveTransaction_Success() throws BaseException {
        // Создаем экземпляр Transaction для сохранения
        Transaction transaction = new Transaction("debit", new BigDecimal("100"), "2");

        // Устанавливаем ожидаемое поведение для мок-объекта transactionRepository
        when(transactionRepository.getById("2")).thenReturn(null);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Вызываем метод save из transactionService
        Transaction savedTransaction = transactionServiceImpl.save(transaction);

        // Проверяем, что результат сохранения равен ожидаемому объекту
        assertThat(savedTransaction).isEqualTo(transaction);
    }

    @Test
    public void testSaveTransactionNotUniqIDException() throws BaseException {
        // Создаем экземпляр Transaction для сохранения
        Transaction transaction = new Transaction("debit",new BigDecimal("100"),"2");
        transaction.setId("testId");

        // Устанавливаем ожидаемое поведение для мок-объекта transactionRepository
        when(transactionRepository.getById("testId")).thenReturn(transaction);

        // Вызываем метод save из transactionService и ожидаем исключение TransactionNotUniqIDException
        assertThatThrownBy(() -> transactionServiceImpl.save(transaction))
                .isInstanceOf(TransactionNotUniqIDException.class)
                .hasMessage("A transaction with the same ID already exists");
    }

    @Test
    public void testGetAllTransactions_Success() throws BaseException {
        // Создаем список Transaction для имитации результата запроса
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction("debit",new BigDecimal("100"),"2");
        Transaction transaction2 = new Transaction("debit",new BigDecimal("100"),"2");

        // Устанавливаем ожидаемое поведение для мок-объекта transactionRepository
        when(transactionRepository.getAll()).thenReturn(transactionList);

        // Вызываем метод getAll из transactionService
        List<Transaction> retrievedTransactions = transactionServiceImpl.getAll();

        // Проверяем, что результат запроса равен ожидаемому списку
        assertThat(retrievedTransactions).isEqualTo(transactionList);
    }

    @Test
    public void testGetTransactionById_Success() throws BaseException {
        // Создаем экземпляр Transaction для имитации результата запроса
        Transaction transaction = new Transaction("debit",new BigDecimal("100"),"2");
        transaction.setId("testId");

        // Устанавливаем ожидаемое поведение для мок-объекта transactionRepository
        when(transactionRepository.getById("testId")).thenReturn(transaction);

        // Вызываем метод getById из transactionService
        Transaction retrievedTransaction = transactionServiceImpl.getById("testId");

        // Проверяем, что результат запроса равен ожидаемому объекту
        assertThat(retrievedTransaction).isEqualTo(transaction);
    }

    @Test
    public void testGetTransactionByIdTransactionDontExistException() throws BaseException {
        // Устанавливаем ожидаемое поведение для мок-объекта transactionRepository (возвращаем null)
        when(transactionRepository.getById("nonExistentId")).thenReturn(null);

        // Вызываем метод getById из transactionService с несуществующим ID
        // и ожидаем исключение TransactionDontExistException
        assertThatThrownBy(() -> transactionServiceImpl.getById("nonExistentId"))
                .isInstanceOf(TransactionDontExistException.class)
                .hasMessage("Transaction with ID: nonExistentId does not exist");
    }
}
