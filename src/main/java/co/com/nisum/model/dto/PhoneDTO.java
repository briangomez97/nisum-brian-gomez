package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @JsonIgnore
    @JsonProperty("id")
    private String id;

    @JsonProperty("number")
    @Size(max = 14, message = "maximum size of the 'number' is 14 characters")
    @NotNull(message = "'number' is required")
    @Pattern(regexp = "^[0-9]*$", message = "only numbers accepted for this field")
    private String number;

    @JsonProperty("citycode")
    @Size(max = 5, message = "maximum size of the 'citycode' is 5 characters")
    @NotNull(message = "'citycode' is required")
    @Pattern(regexp = "^[0-9]*$", message = "only numbers accepted for this field")
    private String cityCode;

    @JsonProperty("countrycode")
    @Size(max = 5, message = "maximum size of the 'countrycode' is 5 characters")
    @NotNull(message = "'countrycode' is required")
    @Pattern(regexp = "^[0-9]*$", message = "only numbers accepted for this field")
    private String countryCode;

}
