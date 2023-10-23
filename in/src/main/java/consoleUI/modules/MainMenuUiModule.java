package consoleUI.modules;

import applicationServices.exceptions.BaseException;
import applicationServices.services.PlayerService;
import consoleUI.input.ScannerSingleton;
import serviceSingleton.PlayerServiceSingleton;
import model.Player;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The MainMenuUiModule class represents the main menu user interface of the console application.
 * It allows users to log in to their accounts, register new accounts, or close the application.
 *
 * @author Gleb Nickolaenko
 */
public class MainMenuUiModule {

    private final PlayerService playerService;
    private final Scanner scanner;

    /**
     * Initializes a new instance of the MainMenuUiModule class.
     */
    public MainMenuUiModule() {
        this.playerService = PlayerServiceSingleton.getPlayerService();
        this.scanner = ScannerSingleton.getScanner();
    }

    /**
     * Displays the main menu and processes user input.
     */
    public void process() {
        String message = "";
        System.out.println("Welcome to the Console App!");

        while (true) {

            displayMainMenu();
            message = scanner.nextLine();

            if (null == message) {
                System.out.println("Invalid input. Use one of the options above.");
            } else {
                switch (message) {
                    case "1":

                        String authLogin;
                        do {
                            System.out.println("\nEnter login:");
                            authLogin = scanner.nextLine();
                        } while (!isValidLogin(authLogin));

                        try {
                            Player player = playerService.findPlayerByLogin(authLogin);
                            System.out.println("User with this login found.");
                            String authPassword;
                            do {
                                System.out.println("Please enter the password.");
                                authPassword = scanner.nextLine();
                            } while (authPassword.isEmpty());
                            if (player.getPassword().equalsIgnoreCase(authPassword)) {
                                System.out.println("The password is correct, logging in.");
                                new PlayerMenuModule().process(player);
                            } else {
                                System.out.println("Wrong password!");
                            }
                        } catch (BaseException e) {
                            System.err.println(e.getMessage());
                        }

                        break;
                    case "2":
                        loginCreationReminder();
                        String regLogin;
                        do {
                            System.out.println("Enter a unique login:");
                            regLogin = scanner.nextLine();
                        } while (regLogin.isEmpty());
                        String password;
                        do {
                            System.out.println("Enter a password for your account:");
                            password = scanner.nextLine();
                        } while (password.isEmpty());

                        try {
                            playerService.createPlayer(new Player(regLogin, password));
                            System.out.println(
                                    "User creation was successful, a bank account will be created automatically."
                                            + " You can proceed with the authentication.");
                        } catch (BaseException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case "3":
                        System.out.println("Termination of the application process");
                        break;
                }

                if (message.equalsIgnoreCase("3")) {
                    break;
                }
            }
        }
    }

    /**
     * Displays the main menu options for the user to choose from.
     */
    public static void displayMainMenu() {
        System.out.println("Please choose an option:");
        System.out.println("1. Log in to your account");
        System.out.println("2. Register a new account");
        System.out.println("3. Close this App");
        System.out.print("Enter your choice (1/2/3): ");
    }

    /**
     * Provides a reminder for creating a login with certain rules and recommendations.
     */
    public static void loginCreationReminder() {
        System.out.println("Login Creation Reminder:");
        System.out.println("- Your login should contain 1 to 12 characters.");
        System.out.println("- Your login cannot consist of spaces only.");
        System.out.println("- Allowed characters are letters (A-Z, a-z), numbers (0-9), and certain special symbols.");
        System.out.println("- Leading and trailing spaces in the login will be removed.");
        System.out.println("- Examples of valid logins: 'user123', 'john_doe', 'user@example'.");
        System.out.println("- Avoid using sensitive or personal information as your login.");
    }

    public boolean isValidLogin(String login) {
        // Регулярное выражение для валидации логина
        // Допустимые символы: буквы, цифры и определенные спецсимволы; длина от 1 до 12 символов
        final Pattern LOGIN_PATTERN = Pattern.compile("^[\\w@.-]{1,12}$");
        // Проверка на null или только пробельные символы
        if (login == null || login.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }
}
