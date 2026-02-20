package ca.mcgill.ecse321.eventregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PersonCreationRequestDto {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank(message = "Password must not be blank")
    private String password;

    public PersonCreationRequestDto() {} // Needed by Jackson to deserialize JSON

    public PersonCreationRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
