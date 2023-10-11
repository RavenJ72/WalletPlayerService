package consoleUI;

import consoleUI.modules.MainMenuUiModule;

/**
 * The entry point of the console application that launches the main menu module.
 * @author Gleb Nickolaenko
 */
public class ConsoleApplication {
    public static void main(String[] args) {
        // Create an instance of the MainMenuUiModule, which represents the main menu user interface.
        MainMenuUiModule mainMenu = new MainMenuUiModule();

        // Process the main menu, allowing the player to interact with the game.
        mainMenu.process();
    }
}

