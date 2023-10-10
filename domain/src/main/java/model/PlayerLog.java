package model;

import java.time.LocalDateTime;

public class PlayerLog {
    String login;
    String action;
    String result;
    private LocalDateTime creationTime;

    public PlayerLog(String login, String action, String result) {
        this.login = login;
        this.action = action;
        this.result = result;
        this.creationTime = LocalDateTime.now();
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Player: " + login + "\n" +
                "Action: " + action + "\n" +
                "Result: " + result + "\n"+
                "Creation Time: " + creationTime + "\n";
    }


}
