package consoleUI.modules;

import applicationServices.exceptions.BaseException;
import applicationServices.services.BankAccountServiceI;
import applicationServices.services.PlayerLogServiceI;
import applicationServices.services.PlayerServiceI;
import applicationServices.services.TransactionServiceI;
import consoleUI.input.ScannerFactory;
import serviceFactories.BankAccountServiceFactory;
import serviceFactories.PlayerLogServiceFactory;
import serviceFactories.PlayerServiceFactory;
import serviceFactories.TransactionServiceFactory;
import model.Player;
import model.PlayerLog;

import java.util.Scanner;

/**
 * The AdminMenuModule class represents the user interface for administrative actions in the console application.
 * It allows administrators to view player logs, get a list of all players, and log out of the system.
 * @author Gleb Nickolaenko
 */
public class AdminMenuModule {

    private final TransactionServiceI transactionService;
    private final BankAccountServiceI bankAccountService;
    private final PlayerServiceI playerService;
    private final PlayerLogServiceI playerLogService;
    private final Scanner scanner;

    /**
     * Initializes a new instance of the AdminMenuModule class.
     */
    public AdminMenuModule() {
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.bankAccountService = BankAccountServiceFactory.getBankAccountService();
        this.playerService = PlayerServiceFactory.getPlayerService();
        this.scanner = ScannerFactory.getScanner();
        this.playerLogService = PlayerLogServiceFactory.getPlayerLogService();
    }

    /**
     * Displays the admin actions menu and processes user input.
     * @param currentPlayer The currently logged-in player, who is an administrator.
     */
    public void process(Player currentPlayer) {
        String message = "";

        while (true) {
            displayAdminActionsMenu();
            message = scanner.nextLine();

            switch (message) {
                case "1":
                    System.out.println("Select a player and enter their username to view their logs.");
                    String login;
                    do {
                        login = scanner.nextLine();
                    } while (login.isEmpty());

                    try {
                        playerLogService.getByLogin(login).forEach(System.out::println);
                        playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Checking player " + currentPlayer.getLogin() + " logs", "Success"));
                        System.out.println();
                    } catch (BaseException e) {
                        playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Checking player " + currentPlayer.getLogin() + " logs", "Fail"));
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    playerService.getAll().forEach(System.out::println);
                    playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Checking all players", "Success"));
                    break;

                case "3":
                    System.out.println("Termination of the application process");
                    break;
            }

            if (message.equalsIgnoreCase("3")) {
                System.out.println("The " + currentPlayer.getLogin() + " has logged out of the system.");
                playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Logout", "Success"));
                break;
            }
        }
    }

    /**
     * Displays the menu of available admin actions.
     */
    public static void displayAdminActionsMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. View player's log");
        System.out.println("2. Get all players");
        System.out.println("3. Log out");
        System.out.print("Enter your choice (1/2/3): ");
    }
}
