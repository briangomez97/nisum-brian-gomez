package co.com.nisum.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {

    @Max(20)
    @NotBlank
    private String name;

    @Max(100)
    @Email
    @NotBlank
    private String email;

    @Max(100)
    @NotBlank
    private String password;

}
