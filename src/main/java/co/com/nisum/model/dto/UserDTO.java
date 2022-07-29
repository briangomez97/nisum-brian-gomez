package co.com.nisum.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDTO {

    @Size(max = 20, message = "maximum size of the 'name' is 20 characters")
    @NotBlank(message = "'name' is required")
    private String name;

    @Size(max = 100, message = "maximum size of the 'email' is 100 characters")
    @Email(message = "invalid 'email' format")
    @NotBlank(message = "'email' is required")
    private String email;

    @Size(max = 100, message = "maximum size of the 'password' is 100 characters")
    @NotBlank(message = "'password' is required")
    private String password;

}
