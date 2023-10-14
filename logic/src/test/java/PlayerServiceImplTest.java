import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import applicationServices.exceptions.BaseException;
import applicationServices.exceptions.bankAccount.BankAccountNotFoundException;
import applicationServices.exceptions.player.PlayerDontExistException;
import applicationServices.exceptions.player.PlayerInvalidLoginException;
import applicationServices.exceptions.player.PlayerNotUniqLoginException;
import applicationServices.services.BankAccountService;
import model.BankAccount;
import model.Player;
import model.Transaction;
import modelRepositoriesI.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.PlayerServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImplTest {
    private PlayerServiceImpl playerServiceImpl;
    private PlayerRepository playerRepository;
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setUp() {
        playerRepository = mock(PlayerRepository.class);
        bankAccountService = mock(BankAccountService.class);
        playerServiceImpl = new PlayerServiceImpl(playerRepository, bankAccountService);
    }

    @Test
    public void testCreatePlayer_Success() throws BaseException {
        Player player = new Player("testLogin", "testPassword", null);

        when(playerRepository.getByLogin("testLogin")).thenReturn(null);
        when(bankAccountService.save(any(BankAccount.class))).thenAnswer(invocation -> {
            BankAccount bankAccount = invocation.getArgument(0);
            bankAccount.setId("testAccountId");
            return bankAccount;
        });
        when(playerRepository.save(player)).thenReturn(player);

        Player createdPlayer = playerServiceImpl.createPlayer(player);

        assertThat(createdPlayer).isEqualTo(player);
        assertThat(createdPlayer.getBankAccountId()).isEqualTo("testAccountId");
    }

    @Test
    public void testCreatePlayerInvalidLoginException() {
        Player player = new Player("invalid login with spaces", "testPassword", null);

        assertThatThrownBy(() -> playerServiceImpl.createPlayer(player))
                .isInstanceOf(PlayerInvalidLoginException.class)
                .hasMessage("Invalid login format");
    }

    @Test
    public void testCreatePlayerNotUniqLoginException() {
        Player existingPlayer = new Player("existingPlayer", "testPassword", null);
        Player newPlayer = new Player("existingPlayer", "testPassword", null);

        when(playerRepository.getByLogin("existingPlayer")).thenReturn(existingPlayer);

        assertThatThrownBy(() -> playerServiceImpl.createPlayer(newPlayer))
                .isInstanceOf(PlayerNotUniqLoginException.class)
                .hasMessage("A user with this username already exists.");
    }

    @Test
    public void testFindPlayerByLogin_Success() throws BaseException {
        Player player = new Player("testLogin", "testPassword", null);

        when(playerRepository.getByLogin("testLogin")).thenReturn(player);

        Player foundPlayer = playerServiceImpl.findPlayerByLogin("testLogin");

        assertThat(foundPlayer).isEqualTo(player);
    }

    @Test
    public void testFindPlayerByLoginDontExistException() {
        when(playerRepository.getByLogin("nonExistentPlayer")).thenReturn(null);

        assertThatThrownBy(() -> playerServiceImpl.findPlayerByLogin("nonExistentPlayer"))
                .isInstanceOf(PlayerDontExistException.class)
                .hasMessage("The specified player doesn't exist");
    }

    @Test
    public void testCheckBalance_Success() throws BaseException {
        String bankAccountId = "testAccountId";
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(BigDecimal.valueOf(100));

        when(bankAccountService.findAccountById(bankAccountId)).thenReturn(bankAccount);

        BigDecimal balance = playerServiceImpl.checkBalance(bankAccountId);

        assertThat(balance).isEqualByComparingTo(BigDecimal.valueOf(100));
    }

    @Test
    public void testGetTransactionHistory_Success() throws BaseException {
        String bankAccountId = "testAccountId";
        List<Transaction> transactions = new ArrayList<>();

        Transaction testTran1 = new Transaction( "debit", BigDecimal.valueOf(50), bankAccountId);
        testTran1.setCreationTime(null);
        Transaction testTran2 = new Transaction( "credit", BigDecimal.valueOf(50), bankAccountId);
        testTran2.setCreationTime(null);

        transactions.add(testTran1);
        transactions.add(testTran2);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setPlayerTransactions(transactions);



        when(bankAccountService.findAccountById(bankAccountId)).thenReturn(bankAccount);

        List<Transaction> history = playerServiceImpl.getTransactionHistory(bankAccountId);

        assertThat(history).isEqualTo(transactions);
    }

    @Test
    public void testGetTransactionHistoryBankAccountNotFoundException() throws BaseException {
        String bankAccountId = "nonExistentAccountId";

        when(bankAccountService.findAccountById(bankAccountId)).thenThrow(new BankAccountNotFoundException("Account not found."));

        assertThatThrownBy(() -> playerServiceImpl.getTransactionHistory(bankAccountId))
                .isInstanceOf(BankAccountNotFoundException.class)
                .hasMessage("Account not found.");
    }
}