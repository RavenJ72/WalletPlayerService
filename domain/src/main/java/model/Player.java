package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A class representing a player in the system.
 * @author Gleb Nickolaenko
 */
@Getter
@Setter
@NoArgsConstructor
public class Player {
    private Long id;
    private String login;
    private String password;
    private String playerRole;
    private Long bankAccountId;

    public Player(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "\nPlayer Information:" +
                "\nLogin: " + login +
                "\nPlayer Role: " + playerRole +
                "\nBank Account ID: " + bankAccountId + "\n";
    }

}
