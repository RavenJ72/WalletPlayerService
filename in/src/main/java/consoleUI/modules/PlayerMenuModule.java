package consoleUI.modules;

import applicationServices.exceptions.BaseException;
import applicationServices.services.*;
import consoleUI.serviceFactories.*;
import consoleUI.input.ScannerFactory;

import model.Player;
import model.PlayerLog;

import java.math.BigDecimal;
import java.util.Scanner;

public class PlayerMenuModule {

    private final TransactionServiceI transactionService;
    private final BankAccountServiceI bankAccountService;
    private final PlayerServiceI playerService;
    private final PlayerLogServiceI playerLogService;
    private final Scanner scanner;

    public PlayerMenuModule() {
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.bankAccountService = BankAccountServiceFactory.getBankAccountService();
        this.playerService = PlayerServiceFactory.getPlayerService();
        this.playerLogService = PlayerLogServiceFactory.getPlayerLogService();
        this.scanner = ScannerFactory.getScanner();
    }

    public void process(Player currentPlayer) {
        String message = "";
        System.out.println("Player  " + currentPlayer.getLogin() + " has logged into the system.\n");

        playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Login","Success"));

        while (true) {
            if(currentPlayer.getPlayerRole() == "ADMIN"){
                new AdminMenuModule().process(currentPlayer);
                break;
            }else{
                try {
                    displayActionsMenu();
                    message = scanner.nextLine();
                    if (null == message) {
                        System.out.println("Invalid input. Use one from above");
                    } else {
                        switch (message) {
                            case "1":
                                String withdrawAmount;
                                do {
                                    System.out.print("Enter the withdrawal amount: ");
                                    withdrawAmount = scanner.nextLine().trim(); // Remove leading and trailing spaces
                                    withdrawAmount = withdrawAmount.replace(',', '.'); // Replace commas with dots
                                } while (withdrawAmount.isEmpty() || withdrawAmount.equals(".")); // Check that the string is not empty and does not consist only of dots
                                BigDecimal withdrawalAmount = new BigDecimal(withdrawAmount);

                                String debitTransactionID = transactionIdAsker(scanner);

                                try {
                                    if (playerService.withdrawMoney(currentPlayer.getBankAccountId(), withdrawalAmount, debitTransactionID)) {
                                        System.out.println("Debit operation was successful");
                                        playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Withdraw money","Success"));
                                    }
                                } catch (BaseException e) {
                                    playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Withdraw money","Fail"));
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case "2":
                                String creditAmountString;
                                do {
                                    System.out.print("Enter the deposit amount: ");
                                    creditAmountString = scanner.nextLine().trim(); // Remove leading and trailing spaces
                                    creditAmountString = creditAmountString.replace(',', '.'); // Replace commas with dots
                                } while (creditAmountString.isEmpty() || creditAmountString.equals(".")); // Check that the string is not empty and does not consist only of dots

                                BigDecimal depositAmount = new BigDecimal(creditAmountString);

                                String creditTransactionId = transactionIdAsker(scanner);
                                try {
                                    if (playerService.depositMoney(currentPlayer.getBankAccountId(), depositAmount, creditTransactionId)) {
                                        System.out.println("Credit operation was successful");
                                        playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Deposit money","Success"));
                                    }
                                } catch (BaseException e) {
                                    playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Deposit money","Fail"));
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case "3":
                                System.out.println("Your balance is: " + playerService.checkBalance(currentPlayer.getBankAccountId()).toString());
                                playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Checking balance","Success"));
                                System.out.println();
                                break;
                            case "4":
                                playerService.getTransactionHistory(currentPlayer.getBankAccountId()).forEach(System.out::println);
                                playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Checking transaction history","Success"));
                                System.out.println();
                                break;
                            case "5":
                                System.out.println("Termination of the application process");
                                break;
                        }
                        if (message.equalsIgnoreCase("5")) {

                            System.out.println("The " + currentPlayer.getLogin() + " has logged out of the system.");
                            playerLogService.save(new PlayerLog(currentPlayer.getLogin(),"Logout","Success"));
                            break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void displayActionsMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Withdraw money from your account");
        System.out.println("2. Deposit money into your account");
        System.out.println("3. Check your account balance");
        System.out.println("4. View transaction history");
        System.out.println("5. Log out");
        System.out.print("Enter your choice (1/2/3/4/5): ");
    }

    public static String transactionIdAsker(Scanner scanner) {

        System.out.println("Generate a unique transaction ID? Type Y/N");
        String yesNo = scanner.nextLine();
        String transactionID;
        if (yesNo.equalsIgnoreCase("y")) {
            transactionID = null;
        } else {
            do {
                System.out.print("Enter ID for transaction (1-36 characters, not empty): ");
                transactionID = scanner.nextLine().trim(); // Remove leading and trailing spaces
            } while (transactionID.isEmpty() || transactionID.length() > 36);

        }
        return transactionID;
    }

}
