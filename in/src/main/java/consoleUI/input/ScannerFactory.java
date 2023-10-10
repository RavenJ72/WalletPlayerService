package consoleUI.input;

import java.util.Scanner;

public class ScannerFactory {
    private static Scanner scannerInstance;
    public static Scanner getScanner(){
        if(scannerInstance == null){
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }
}
