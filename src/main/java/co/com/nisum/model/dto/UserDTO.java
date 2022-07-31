package co.com.nisum.model.dto;

import co.com.nisum.model.dto.validation.CustomEmail;
import co.com.nisum.model.dto.validation.CustomPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class UserDTO {

    @JsonIgnore
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @Size(max = 40, message = "maximum size of the 'name' is 40 characters")
    @NotNull(message = "'name' is required")
    private String name;

    @JsonProperty("email")
    @Size(max = 100, message = "maximum size of the 'email' is 100 characters")
    @CustomEmail(message = "invalid 'email' format")
    @NotNull(message = "'email' is required")
    private String email;

    @JsonProperty("password")
    @Size(max = 100, message = "maximum size of the 'password' is 100 characters")
    @CustomPassword(message = "invalid 'password' format")
    @NotNull(message = "'password' is required")
    private String password;

    @JsonProperty("phones")
    @NotEmpty(message = "'phones' cannot be empty")
    @NotNull(message = "'phones' is required")
    @Valid
    private List<PhoneDTO> phones;

    @JsonProperty("created")
    private LocalDateTime created;

    @JsonProperty("modified")
    private LocalDateTime modified;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isactive")
    private Boolean isActive;

    public Boolean getIsActive() {
        return this.isActive != null ? this.isActive : Boolean.TRUE;
    }

}
