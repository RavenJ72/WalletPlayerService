import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.playerLog.PlayerLogDontExistException;
import model.PlayerLog;
import modelRepositoriesI.PlayerLogRepositoryI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.PlayerLogService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlayerLogServiceTest {
    private PlayerLogService playerLogService;
    private PlayerLogRepositoryI playerLogRepository;

    @BeforeEach
    public void setUp() {
        playerLogRepository = mock(PlayerLogRepositoryI.class);
        playerLogService = new PlayerLogService(playerLogRepository);
    }

    @Test
    public void testSave_Success() {
        PlayerLog playerLog = new PlayerLog("testLogin", "action1", "Success");

        when(playerLogRepository.save(playerLog)).thenReturn(playerLog);

        PlayerLog savedLog = playerLogService.save(playerLog);

        assertThat(savedLog).isEqualTo(playerLog);
    }

    @Test
    public void testGetByLogin_Success() throws BaseException {
        String login = "testLogin";
        List<PlayerLog> playerLogs = new ArrayList<>();
        playerLogs.add(new PlayerLog(login, "action1", "Success"));
        playerLogs.add(new PlayerLog(login, "action2", "Success"));

        when(playerLogRepository.getByPlayerLogsByLogin(login)).thenReturn(playerLogs);

        List<PlayerLog> retrievedLogs = playerLogService.getByLogin(login);

        assertThat(retrievedLogs).isEqualTo(playerLogs);
    }

    @Test
    public void testGetByLoginPlayerLogDontExistException() {
        String login = "nonExistentLogin";

        when(playerLogRepository.getByPlayerLogsByLogin(login)).thenReturn(null);

        assertThatThrownBy(() -> playerLogService.getByLogin(login))
                .isInstanceOf(PlayerLogDontExistException.class)
                .hasMessage("Log does not exist.");
    }
}
