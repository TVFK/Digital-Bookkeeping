package ru.taf.digitalbookkeepingwithboot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {
    @NotEmpty(message = "Name mustn't be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Min(value = 1920, message = "Year of birth must be greater than 1920")
    @Max(value = 2024, message = "Year of birth must be less than 2024")
    private int birthYear;

    private String password;

}
