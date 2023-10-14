package model;

import java.time.LocalDateTime;

/**
 * A class representing a log entry for a player's actions.
 * @author Gleb Nickolaenko
 */
public class PlayerLog {
    private String login;
    private String action;
    private String result;
    private LocalDateTime creationTime;

    /**
     * Constructs a new PlayerLog entry with the provided login, action, and result.
     *
     * @param login  The login of the player associated with the log entry.
     * @param action The action performed by the player.
     * @param result The result or outcome of the action.
     */
    public PlayerLog(String login, String action, String result) {
        this.login = login;
        this.action = action;
        this.result = result;
        this.creationTime = LocalDateTime.now();
    }

    /**
     * Get the login of the player associated with the log entry.
     *
     * @return The player's login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get the action performed by the player.
     *
     * @return The player's action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Get the result or outcome of the action.
     *
     * @return The result of the action.
     */
    public String getResult() {
        return result;
    }

    /**
     * Returns a string representation of the player log entry.
     *
     * @return A formatted string containing player log information.
     */
    @Override
    public String toString() {
        return "Player: " + login + "\n" +
                "Action: " + action + "\n" +
                "Result: " + result + "\n" +
                "Creation Time: " + creationTime + "\n";
    }
}
