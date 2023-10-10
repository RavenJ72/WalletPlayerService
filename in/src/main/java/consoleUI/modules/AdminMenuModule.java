package consoleUI.modules;

import applicationServices.exceptions.BaseException;
import applicationServices.services.BankAccountServiceI;
import applicationServices.services.PlayerLogServiceI;
import applicationServices.services.PlayerServiceI;
import applicationServices.services.TransactionServiceI;
import consoleUI.input.ScannerFactory;
import consoleUI.serviceFactories.BankAccountServiceFactory;
import consoleUI.serviceFactories.PlayerLogServiceFactory;
import consoleUI.serviceFactories.PlayerServiceFactory;
import consoleUI.serviceFactories.TransactionServiceFactory;
import model.Player;
import model.PlayerLog;

import java.util.Scanner;

public class AdminMenuModule {
    private final TransactionServiceI transactionService;
    private final BankAccountServiceI bankAccountService;
    private final PlayerServiceI playerService;
    private final PlayerLogServiceI playerLogServiceI;
    private final Scanner scanner;

    public AdminMenuModule() {
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.bankAccountService = BankAccountServiceFactory.getBankAccountService();
        this.playerService = PlayerServiceFactory.getPlayerService();
        this.scanner = ScannerFactory.getScanner();
        this.playerLogServiceI = PlayerLogServiceFactory.getPlayerLogService();
    }

    public void process(Player currentPlayer) {
        String message = "";

        while (true) {

            displayAdminActionsMenu();
            message = scanner.nextLine();
            switch (message){
            case "1":
                System.out.println("Select a player and enter their username to view their logs.");
                String login;
                do{
                    login = scanner.nextLine();
                }while(login.isEmpty());
                try {
                    playerLogServiceI.getByLogin(login).forEach(System.out::println);
                    playerLogServiceI.save(new PlayerLog(currentPlayer.getLogin(),"checking player " + currentPlayer.getLogin() + " logs","Success"));
                    System.out.println();
                }catch (BaseException e){
                    playerLogServiceI.save(new PlayerLog(currentPlayer.getLogin(),"checking player " + currentPlayer.getLogin() + " logs","Fail"));
                    System.out.println(e.getMessage());
                }
                break;
            case "2":
                playerService.getAll().forEach(System.out::println);
                playerLogServiceI.save(new PlayerLog(currentPlayer.getLogin(),"checking all players","Success"));
                break;
            case "3":
                    System.out.println("Termination of the application process");
                    break;

        }
        if (message.equalsIgnoreCase("3")) {
            System.out.println("The " + currentPlayer.getLogin() + " has logged out of the system.");
            playerLogServiceI.save(new PlayerLog(currentPlayer.getLogin(),"Logout","Success"));
            break;
        }


        }

    }
    public static void displayAdminActionsMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. View player's log");
        System.out.println("2. Get all players");
        System.out.println("3. Log out");
        System.out.print("Enter your choice (1/2): ");
    }
}
