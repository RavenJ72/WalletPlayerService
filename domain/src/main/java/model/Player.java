package model;

import java.time.LocalDateTime;

public class Player {
    private String login;
    private String email;
    private String password;
    private LocalDateTime creationTime;
    private String playerRole;

    private String bankAccountId;

    public Player(String nickname, String email, String password, String bankAccountId) {
        this.login = nickname;
        this.email = email;
        this.password = password;
        this.bankAccountId = bankAccountId;
        this.creationTime = LocalDateTime.now();
        this.playerRole = "user";
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public String getPlayerRole() {
        return playerRole;
    }
}
