package model;

import java.time.LocalDateTime;

/**
 * A class representing a player in the system.
 * @author Gleb Nickolaenko
 */
public class Player {
    private String login;
    private String password;
    private String playerRole;
    private String bankAccountId;

    /**
     * Constructs a new Player with the provided nickname, password, and bank account ID.
     *
     * @param nickname     The login nickname of the player.
     * @param password     The password associated with the player's account.
     * @param bankAccountId The unique ID of the player's associated bank account.
     */
    public Player(String nickname, String password, String bankAccountId) {
        this.login = nickname;
        this.password = password;
        this.bankAccountId = bankAccountId;
        this.playerRole = "USER";
    }

    /**
     * Get the login nickname of the player.
     *
     * @return The login nickname.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get the password associated with the player's account.
     *
     * @return The player's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the creation time of the player's account.
     *
     * @return The creation time.
     */


    /**
     * Get the unique ID of the player's associated bank account.
     *
     * @return The bank account ID.
     */
    public String getBankAccountId() {
        return bankAccountId;
    }

    /**
     * Get the role of the player in the system.
     *
     * @return The player's role.
     */
    public String getPlayerRole() {
        return playerRole;
    }

    /**
     * Set the role of the player in the system.
     *
     * @param playerRole The new role to set.
     */
    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    /**
     * Set the unique ID of the player's associated bank account.
     *
     * @param bankAccountId The new bank account ID to set.
     */
    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    /**
     * Returns a string representation of the player's information.
     *
     * @return A formatted string containing player information.
     */
    @Override
    public String toString() {
        return "\nPlayer Information:" +
                "\nLogin: " + login +
                "\nPlayer Role: " + playerRole +
                "\nBank Account ID: " + bankAccountId + "\n";
    }
}
