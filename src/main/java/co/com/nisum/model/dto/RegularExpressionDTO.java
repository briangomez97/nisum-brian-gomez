package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class RegularExpressionDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @Size(max = 40, message = "maximum size of the 'name' is 40 characters")
    @NotNull(message = "'name' is required")
    private String name;

    @JsonProperty("regularexpression")
    @Size(max = 50, message = "maximum size of the 'regularexpression' is 20 characters")
    @NotNull(message = "'regularexpression' is required")
    private String regularExpression;

    @JsonProperty("lastupdate")
    @NotNull(message = "'lastupdate' is required")
    private LocalDateTime lastUpdate;
}
