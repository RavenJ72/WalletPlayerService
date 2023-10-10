package consoleUI.modules;

import applicationServices.exceptions.player.PlayerDontExistException;
import applicationServices.exceptions.player.PlayerNotUniqLoginException;
import applicationServices.services.PlayerLogServiceI;
import applicationServices.services.PlayerServiceI;
import consoleUI.input.ScannerFactory;
import consoleUI.serviceFactories.PlayerLogServiceFactory;
import consoleUI.serviceFactories.PlayerServiceFactory;
import model.Player;

import java.util.Scanner;



public class MainMenuUiModule {


    private final PlayerServiceI playerService;
    private final Scanner scanner;


    public MainMenuUiModule() {
        this.playerService = PlayerServiceFactory.getPlayerService();
        this.scanner = ScannerFactory.getScanner();
    }


    public void process(){
        String message = "";
        System.out.println("Welcome to the Console App!");
        while (true){
            try{
                displayMainMenu();
                message = scanner.nextLine();
                if (null == message) {
                    System.out.println("Invalid input. Use one from above");
                } else {
                    switch (message) {
                        case "1":
                            System.out.println("\nEnter login:");
                            String authLogin = scanner.nextLine();
                            try {
                                Player player = playerService.findPlayerByLogin(authLogin);
                                System.out.println("User with this login found.");
                                String authPassword;
                                do {
                                    System.out.println("Please enter the password.");
                                    authPassword = scanner.nextLine();
                                } while (authPassword.isEmpty());
                                if(player.getPassword().equalsIgnoreCase(authPassword)){
                                    System.out.println("The password is correct, logging in.");
                                    new PlayerMenuModule().process(player);
                                    break;
                                }else{
                                    System.out.println("Wrong password!");
                                }
                            }catch (PlayerDontExistException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
                            loginCreationReminder();
                            String regLogin;
                            do {
                                System.out.println(String.format("Enter uniq login"));
                                regLogin = scanner.nextLine();
                            } while (regLogin.isEmpty());
                            String password;
                            do {
                                System.out.println(String.format("Enter password for your acc"));
                                password = scanner.nextLine();
                            } while (password.isEmpty());
                            try {
                                playerService.createPlayer(new Player(regLogin,password,null));
                                System.out.println(
                                        "User creation was successful, a bank account will be created automatically." +
                                        " You can proceed with the authentication.");
                            }catch (PlayerNotUniqLoginException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            System.out.println("Termination of the application process");
                    }
                    if (message.equalsIgnoreCase("3")){
                        break;
                    }
                }


            }catch (Exception e){
                System.out.println(e.getMessage());
            }


        }
    }

    public static void displayMainMenu() {

        System.out.println("Please choose an option:");
        System.out.println("1. Log in to your account");
        System.out.println("2. Register a new account");
        System.out.println("3. Close this App");
        System.out.print("Enter your choice (1/2): ");
    }
    public static void loginCreationReminder(){
        System.out.println("Login Creation Reminder:");
        System.out.println("- Your login should contain 1 to 12 characters.");
        System.out.println("- Your login cannot consist of spaces only.");
        System.out.println("- Allowed characters are letters (A-Z, a-z), numbers (0-9), and certain special symbols.");
        System.out.println("- Leading and trailing spaces in the login will be removed.");
        System.out.println("- Examples of valid logins: 'user123', 'john_doe', 'user@example'.");
        System.out.println("- Avoid using sensitive or personal information as your login.");

    }



}
