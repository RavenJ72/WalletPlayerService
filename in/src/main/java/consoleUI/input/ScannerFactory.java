package consoleUI.input;

import java.util.Scanner;

/**
 * A utility class for creating and managing a single instance of a Java Scanner for user input from the console.
 *
 * This class ensures that only one instance of a Scanner is created, promoting resource efficiency and preventing
 * multiple Scanners from competing for console input.
 *
 * @author Gleb Nickolaenko
 */
public class ScannerFactory {
    private static Scanner scannerInstance;

    /**
     * Retrieves the single instance of the Scanner for console input. If the instance does not exist, it is created.
     *
     * @return The single instance of the Scanner for console input.
     */
    public static Scanner getScanner() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }
}
