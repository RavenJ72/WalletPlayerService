package model;

import java.time.LocalDateTime;

public class Player {
    private String login;

    private String password;
    private LocalDateTime creationTime;
    private String playerRole;

    private String bankAccountId;

    public Player(String nickname, String password, String bankAccountId) {
        this.login = nickname;

        this.password = password;
        this.bankAccountId = bankAccountId;
        this.creationTime = LocalDateTime.now();
        this.playerRole = "USER";
    }

    public String getLogin() {
        return login;
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

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Override
    public String toString() {
        return "\nPlayer Information:" +
                "\nLogin: " + login +
                "\nCreation Time: " + creationTime +
                "\nPlayer Role: " + playerRole +
                "\nBank Account ID: " + bankAccountId + "\n";
    }

}
