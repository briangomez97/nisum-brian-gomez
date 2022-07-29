package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @Size(max = 40, message = "maximum size of the 'name' is 40 characters")
    @NotNull(message = "'name' is required")
    private String name;

    @JsonProperty("email")
    @Size(max = 100, message = "maximum size of the 'email' is 100 characters")
    @Email(message = "invalid 'email' format")
    @NotNull(message = "'email' is required")
    private String email;

    @JsonProperty("password")
    @Size(max = 100, message = "maximum size of the 'password' is 100 characters")
    @NotNull(message = "'password' is required")
    private String password;

    @JsonProperty("phones")
    @NotEmpty(message = "'phones' cannot be empty")
    @NotNull(message = "'phones' is required")
    @Valid
    private List<PhoneDTO> phones;

}
