package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PhoneDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("number")
    @Size(max = 14, message = "maximum size of the 'number' is 14 characters")
    @NotNull(message = "'number' is required")
    private String number;

    @JsonProperty("citycode")
    @Size(max = 5, message = "maximum size of the 'citycode' is 5 characters")
    @NotNull(message = "'citycode' is required")
    private String cityCode;

    @JsonProperty("countrycode")
    @Size(max = 5, message = "maximum size of the 'countrycode' is 5 characters")
    @NotNull(message = "'countrycode' is required")
    private String countryCode;

}
