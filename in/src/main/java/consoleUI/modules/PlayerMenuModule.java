package consoleUI.modules;

import applicationServices.exceptions.BaseException;
import applicationServices.services.*;
import consoleUI.input.ScannerSingleton;
import model.Player;
import model.PlayerLog;
import serviceFactories.BankAccountServiceSingleton;
import serviceFactories.PlayerLogServiceSingleton;
import serviceFactories.PlayerServiceSingleton;
import serviceFactories.TransactionServiceSingleton;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Represents the user interface module for player actions in the application.
 * It allows players to perform actions such as withdrawing money, depositing money, checking account balance,
 * viewing transaction history, and logging out.
 * @author Gleb Nickolaenko
 */
public class PlayerMenuModule {

    private final TransactionService transactionService;
    private final BankAccountService bankAccountService;
    private final PlayerService playerService;
    private final PlayerLogService playerLogService;
    private final Scanner scanner;

    /**
     * Constructs a new PlayerMenuModule.
     */
    public PlayerMenuModule() {
        this.transactionService = TransactionServiceSingleton.getTransactionService();
        this.bankAccountService = BankAccountServiceSingleton.getBankAccountService();
        this.playerService = PlayerServiceSingleton.getPlayerService();
        this.playerLogService = PlayerLogServiceSingleton.getPlayerLogService();
        this.scanner = ScannerSingleton.getScanner();
    }

    /**
     * Processes player actions, allowing them to interact with the application.
     *
     * @param currentPlayer The currently logged-in player.
     */
    public void process(Player currentPlayer) {
        String message = "";
        System.out.println("Player " + currentPlayer.getLogin() + " has logged into the system.\n");

        playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Login", "Success"));

        while (true) {
            if (currentPlayer.getPlayerRole().equals("ADMIN")) {
                new AdminMenuModule().process(currentPlayer);
                break;
            } else {
                try {
                    displayActionsMenu();
                    message = scanner.nextLine();
                    if (null == message) {
                        System.out.println("Invalid input. Use one of the options listed above.");
                    } else {
                        switch (message) {
                            case "1":
                                // Withdraw Money
                                String withdrawAmount;
                                do {
                                    System.out.print("Enter the withdrawal amount: ");
                                    withdrawAmount = scanner.nextLine().trim();
                                    withdrawAmount = withdrawAmount.replace(',', '.');
                                } while (withdrawAmount.isEmpty() || withdrawAmount.equals("."));
                                BigDecimal withdrawalAmount = new BigDecimal(withdrawAmount);
                                String debitTransactionID = transactionIdAsker(scanner);
                                try {
                                    if (playerService.withdrawMoney(currentPlayer.getBankAccountId(), withdrawalAmount, debitTransactionID)) {
                                        System.out.println("Debit operation was successful");
                                        playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Withdraw money", "Success"));
                                    }
                                } catch (BaseException e) {
                                    playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Withdraw money", "Fail"));
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case "2":
                                // Deposit Money
                                String creditAmountString;
                                do {
                                    System.out.print("Enter the deposit amount: ");
                                    creditAmountString = scanner.nextLine().trim();
                                    creditAmountString = creditAmountString.replace(',', '.');
                                } while (creditAmountString.isEmpty() || creditAmountString.equals("."));
                                BigDecimal depositAmount = new BigDecimal(creditAmountString);
                                String creditTransactionId = transactionIdAsker(scanner);
                                try {
                                    if (playerService.depositMoney(currentPlayer.getBankAccountId(), depositAmount, creditTransactionId)) {
                                        System.out.println("Credit operation was successful");
                                        playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Deposit money", "Success"));
                                    }
                                } catch (BaseException e) {
                                    playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Deposit money", "Fail"));
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case "3":
                                // Check Account Balance
                                System.out.println("Your balance is: " + playerService.checkBalance(currentPlayer.getBankAccountId()).toString());
                                playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Checking balance", "Success"));
                                System.out.println();
                                break;

                            case "4":
                                // View Transaction History
                                playerService.getTransactionHistory(currentPlayer.getBankAccountId()).forEach(System.out::println);
                                playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Checking transaction history", "Success"));
                                System.out.println();
                                break;

                            case "5":
                                // Log Out
                                System.out.println("Terminating the application process");
                                break;
                        }

                        if (message.equalsIgnoreCase("5")) {
                            System.out.println("Player " + currentPlayer.getLogin() + " has logged out of the system.");
                            playerLogService.save(new PlayerLog(currentPlayer.getLogin(), "Logout", "Success"));
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Displays the list of available actions for the player.
     */
    public static void displayActionsMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Withdraw money from your account");
        System.out.println("2. Deposit money into your account");
        System.out.println("3. Check your account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Log out");
        System.out.print("Enter your choice (1/2/3/4/5): ");
    }

    /**
     * Asks the player whether they want to generate a unique transaction ID or enter one manually.
     *
     * @param scanner The scanner for user input.
     * @return A transaction ID (either generated or entered manually).
     */
    public static String transactionIdAsker(Scanner scanner) {
        System.out.println("Generate a unique transaction ID? Type Y/N");
        String yesNo = scanner.nextLine();
        String transactionID;
        if (yesNo.equalsIgnoreCase("y")) {
            transactionID = null;
        } else {
            do {
                System.out.print("Enter an ID for the transaction (1-36 characters, not empty): ");
                transactionID = scanner.nextLine().trim();
            } while (transactionID.isEmpty() || transactionID.length() > 36);
        }
        return transactionID;
    }
}

