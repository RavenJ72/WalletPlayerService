package consoleUI.modules;

import applicationServices.exceptions.player.PlayerDontExistException;
import applicationServices.exceptions.player.PlayerNotUniqLoginException;
import applicationServices.services.BankAccountServiceI;
import applicationServices.services.PlayerServiceI;
import applicationServices.services.TransactionServiceI;
import consoleUI.input.ScannerFactory;
import consoleUI.serviceFactories.BankAccountServiceFactory;
import consoleUI.serviceFactories.PlayerServiceFactory;
import consoleUI.serviceFactories.TransactionServiceFactory;
import model.Player;

import java.math.BigDecimal;
import java.util.Scanner;

public class PlayerMenuModule {

    private final TransactionServiceI transactionService;
    private final BankAccountServiceI bankAccountService;
    private final PlayerServiceI playerService;
    private final Scanner scanner;

    public PlayerMenuModule() {
        this.transactionService = TransactionServiceFactory.getTransactionService();
        this.bankAccountService = BankAccountServiceFactory.getBankAccountService();
        this.playerService = PlayerServiceFactory.getPlayerService();
        this.scanner = ScannerFactory.getScanner();
    }

    public void process(Player currentPlayer){
        String message = "";
        System.out.println("Player  " + currentPlayer.getLogin() + " has logged into the system.\n");
        while (true){
            try{
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

                            System.out.println("Generate a unique transaction ID? Type Y/N");
                            String yesNo = scanner.nextLine();
                            if(yesNo.equalsIgnoreCase("y")){

                            }else{
                                String id;
                                do {
                                    System.out.print("Enter ID for transaction: ");
                                    id = scanner.nextLine().trim(); // Remove leading and trailing spaces
                                } while (id.isEmpty());
                            }







                            break;
                        case "2":


                            break;
                        case "3":
                            System.out.println(playerService.checkBalance(currentPlayer.getBankAccountId()).toString());
                            System.out.println();
                            break;
                        case "4":
                            playerService.getTransactionHistory(currentPlayer.getBankAccountId()).forEach(System.out::println);
                            System.out.println();
                            break;
                        case "5":
                            System.out.println("Termination of the application process");
                        default:
                            System.out.println("Invalid input. Use one from above");
                            break;
                    }
                    if (message.equalsIgnoreCase("5")){

                        System.out.println("The " + currentPlayer.getLogin() + " has logged out of the system.");
                        break;
                    }
                }


            }catch (Exception e){
                System.out.println(e.getMessage());
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

}
