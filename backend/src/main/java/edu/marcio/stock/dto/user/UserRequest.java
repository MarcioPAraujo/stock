package edu.marcio.stock.dto.user;

import edu.marcio.stock.enums.UserRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "the name of the user should be filled")
    private String nome;

    @NotBlank(message = "the email should be filled")
    @Pattern(regexp = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email")
    private String email;

    @NotBlank(message = "the password is mandatory")
    @Size(min = 8, message = "The password should have at leat 8 characters")
    @Size(max = 30, message = "The password should have a maximun of 30 characters")
    @Pattern(regexp = "[a-z]", message = "The password should contain at least a lower case character")
    @Pattern(regexp = "[A-Z]", message = "The password should contain at least a upper case character")
    @Pattern(regexp = "[0-9]", message = "The password should contain at least a number")
    @Pattern(regexp = ".*(\\W|_).*", message = "The password should contain at least a special character")
    private String password;

    @NotBlank(message = "The role is mandatory")
    @Pattern(regexp = "MANAGER|LEADER|DOC_WORKER", message = "the role should be MANAGER, LEADER or DOC_WORKER")
    private UserRoles role;

    @NotBlank(message = "The sector is mandatory")
    private String sector;

}
