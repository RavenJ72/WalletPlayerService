//import jbdcRepositories.TransactionRepositoryImpl;
//import jbdcRepositories.connection.DatabaseManager;
//import model.Transaction;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Testcontainers
//public class TransactionRepositoryImplTest {
//
//    @Container
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.3")
//            .withDatabaseName("wallet")
//            .withUsername("postgres")
//            .withPassword("admin");
//
//    private static String url;
//    private static TransactionRepositoryImpl transactionRepository;
//
//    @BeforeAll
//    public static void setUp(){
//        url = postgres.getJdbcUrl();
//        DatabaseManager.makeMigrations(url);
//        transactionRepository = new TransactionRepositoryImpl(url);
//    }
//
//    @Test
//    public void saveTest() {
//        Transaction transaction = new Transaction();
//        transaction.setType("CREDIT");
//        transaction.setAmount(new BigDecimal("100.00"));
//        transaction.setBankAccountId(1L);
//
//        Transaction savedTransaction = transactionRepository.save(transaction);
//        Assertions.assertThat(savedTransaction).isNotNull();
//        Assertions.assertThat(savedTransaction.getId()).isNotNull();
//        Assertions.assertThat(savedTransaction.getCreationTime()).isNotNull();
//    }
//
//    @Test
//    public void getAllByIdTest() {
//        Transaction transaction1 = new Transaction();
//        transaction1.setType("CREDIT");
//        transaction1.setAmount(new BigDecimal("100.00"));
//        transaction1.setBankAccountId(1L);
//
//        Transaction transaction2 = new Transaction();
//        transaction2.setType("DEBIT");
//        transaction2.setAmount(new BigDecimal("50.00"));
//        transaction2.setBankAccountId(1L);
//
//        transactionRepository.save(transaction1);
//        transactionRepository.save(transaction2);
//
//        List<Transaction> transactions = transactionRepository.getAllById(1L);
//        Assertions.assertThat(transactions).isNotEmpty();
//        Assertions.assertThat(transactions.size()).isGreaterThanOrEqualTo(2);
//    }
//}
