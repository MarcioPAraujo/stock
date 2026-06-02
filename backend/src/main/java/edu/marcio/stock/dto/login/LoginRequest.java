package edu.marcio.stock.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "The email should not be empty")
    private String email;

    @NotBlank(message = "The password should not be empty")
    private String password;
}
