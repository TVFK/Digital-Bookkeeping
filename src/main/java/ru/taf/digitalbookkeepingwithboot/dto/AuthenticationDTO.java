package ru.taf.digitalbookkeepingwithboot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationDTO {

    @NotEmpty(message = "username mustn't be empty")
    private String userName;

    private String password;

}
