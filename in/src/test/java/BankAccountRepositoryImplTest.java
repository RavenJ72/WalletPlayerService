//import jbdcRepositories.BankAccountRepositoryImpl;
//import jbdcRepositories.TransactionRepositoryImpl;
//import jbdcRepositories.connection.DatabaseManager;
//import model.BankAccount;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.math.BigDecimal;
//
//@Testcontainers
//public class BankAccountRepositoryImplTest {
//
//    @Container
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.3")
//            .withDatabaseName("wallet")
//            .withUsername("postgres")
//            .withPassword("admin");
//
//    private static String url;
//    private static BankAccountRepositoryImpl bankAccountRepository;
//    private static TransactionRepositoryImpl transactionRepository;
//
//    @BeforeAll
//    public static void setUp(){
//        url = postgres.getJdbcUrl();
//        DatabaseManager.makeMigrations(url);
//        transactionRepository = new TransactionRepositoryImpl(url);
//        bankAccountRepository = new BankAccountRepositoryImpl(transactionRepository, url);
//    }
//
//    @Test
//    public void saveTest(){
//        BankAccount bankAccount = new BankAccount();
//        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
//        Assertions.assertThat(savedBankAccount).isNotNull();
//        Assertions.assertThat(savedBankAccount.getId()).isNotNull();
//    }
//
//    @Test
//    public void findByIdTest(){
//        BankAccount bankAccount = new BankAccount();
//        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
//        BankAccount foundBankAccount = bankAccountRepository.findById(savedBankAccount.getId());
//        Assertions.assertThat(foundBankAccount).isNotNull();
//        Assertions.assertThat(foundBankAccount.getId()).isEqualTo(savedBankAccount.getId());
//    }
//
//    @Test
//    public void withdrawMoneyTest(){
//        BankAccount bankAccount = new BankAccount();
//        bankAccount = bankAccountRepository.save(bankAccount);
//
//        boolean result = bankAccountRepository.withdrawMoney(bankAccount.getId(), new BigDecimal("0.00"), null);
//        Assertions.assertThat(result).isTrue();
//    }
//
//    @Test
//    public void depositMoneyTest(){
//        BankAccount bankAccount = new BankAccount();
//        bankAccount = bankAccountRepository.save(bankAccount);
//        boolean result = bankAccountRepository.depositMoney(bankAccount.getId(), new BigDecimal("100.00"), null);
//        Assertions.assertThat(result).isTrue();
//    }
//}
