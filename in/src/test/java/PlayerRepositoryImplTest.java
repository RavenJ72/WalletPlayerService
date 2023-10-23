//import jbdcRepositories.PlayerRepositoryImpl;
//import jbdcRepositories.connection.DatabaseManager;
//import model.Player;
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
//public class PlayerRepositoryImplTest {
//
//    @Container
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.3")
//            .withDatabaseName("wallet")
//            .withUsername("postgres")
//            .withPassword("admin");
//
//    private static String url;
//    private static PlayerRepositoryImpl playerRepository;
//
//    @BeforeAll
//    public static void setUp() {
//        url = postgres.getJdbcUrl();
//        DatabaseManager.makeMigrations(url);
//        playerRepository = new PlayerRepositoryImpl(url);
//    }
//
//    @Test
//    public void saveTest() {
//        Player player = new Player();
//        player.setLogin("player1");
//        player.setPassword("password1");
//
//        Player savedPlayer = playerRepository.save(player);
//        Assertions.assertThat(savedPlayer).isNotNull();
//        Assertions.assertThat(savedPlayer.getId()).isNotNull();
//        Assertions.assertThat(savedPlayer.getBankAccountId()).isNotNull();
//    }
//
//    @Test
//    public void getByLoginTest() {
//        Player player = new Player();
//        player.setLogin("player2");
//        player.setPassword("password2");
//
//        playerRepository.save(player);
//        Player fetchedPlayer = playerRepository.getByLogin("player2");
//
//        Assertions.assertThat(fetchedPlayer).isNotNull();
//        Assertions.assertThat(fetchedPlayer.getLogin()).isEqualTo("player2");
//        Assertions.assertThat(fetchedPlayer.getPassword()).isEqualTo("password2");
//    }
//
//    @Test
//    public void getAllTest() {
//        Player player1 = new Player();
//        player1.setLogin("player3");
//        player1.setPassword("password3");
//
//        Player player2 = new Player();
//        player2.setLogin("player4");
//        player2.setPassword("password4");
//
//        playerRepository.save(player1);
//        playerRepository.save(player2);
//
//        List<Player> players = playerRepository.getAll();
//        Assertions.assertThat(players).isNotEmpty();
//        Assertions.assertThat(players.size()).isGreaterThanOrEqualTo(2);
//    }
//}
