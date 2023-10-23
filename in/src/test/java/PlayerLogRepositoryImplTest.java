//import jbdcRepositories.PlayerLogRepositoryImpl;
//import jbdcRepositories.connection.DatabaseManager;
//import model.PlayerLog;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.List;
//
//@Testcontainers
//public class PlayerLogRepositoryImplTest {
//
//    @Container
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.3")
//            .withDatabaseName("wallet")
//            .withUsername("postgres")
//            .withPassword("admin");
//
//    private static String url;
//    private static PlayerLogRepositoryImpl playerLogRepository;
//
//    @BeforeAll
//    public static void setUp(){
//        url = postgres.getJdbcUrl();
//        DatabaseManager.makeMigrations(url);
//        playerLogRepository = new PlayerLogRepositoryImpl(url);
//    }
//
//    @Test
//    public void saveTest(){
//        PlayerLog playerLog = new PlayerLog();
//        playerLog.setLogin("player1");
//        playerLog.setAction("action1");
//        playerLog.setResult("result1");
//
//        PlayerLog savedPlayerLog = playerLogRepository.save(playerLog);
//        Assertions.assertThat(savedPlayerLog).isNotNull();
//        Assertions.assertThat(savedPlayerLog.getId()).isNotNull();
//        Assertions.assertThat(savedPlayerLog.getCreationTime()).isNotNull();
//    }
//
//    @Test
//    public void getByPlayerLogsByLoginTest(){
//        PlayerLog playerLog1 = new PlayerLog();
//        playerLog1.setLogin("player2");
//        playerLog1.setAction("action1");
//        playerLog1.setResult("result1");
//        playerLogRepository.save(playerLog1);
//
//        PlayerLog playerLog2 = new PlayerLog();
//        playerLog2.setLogin("player2");
//        playerLog2.setAction("action2");
//        playerLog2.setResult("result2");
//        playerLogRepository.save(playerLog2);
//
//        List<PlayerLog> logs = playerLogRepository.getByPlayerLogsByLogin("player2");
//        Assertions.assertThat(logs).isNotEmpty();
//        Assertions.assertThat(logs.size()).isEqualTo(2);
//        Assertions.assertThat(logs).extracting(PlayerLog::getLogin).containsOnly("player2");
//    }
//}
