package ru.taf.digitalbookkeepingwithboot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class AuthenticationDTO {
    @NotEmpty(message = "username mustn't be empty")
    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
