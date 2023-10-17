package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A class representing a log entry for a player's actions.
 * @author Gleb Nickolaenko
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerLog {
    private Long id;
    private String login;
    private String action;
    private String result;
    private LocalDateTime creationTime;

    public PlayerLog(String login, String action, String result) {
        this.login = login;
        this.action = action;
        this.result = result;
    }
    @Override
    public String toString() {
        return "Player: " + login + "\n" +
                "Action: " + action + "\n" +
                "Result: " + result + "\n" +
                "Creation Time: " + creationTime + "\n";
    }
}
