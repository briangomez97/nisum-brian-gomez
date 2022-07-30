package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class RegularExpressionDTO {

    @JsonIgnore
    @JsonProperty("id")
    private String id;

    @JsonIgnore
    @JsonProperty("name")
    private String name;

    @JsonProperty("regularexpression")
    @Size(max = 200, message = "maximum size of the 'regularexpression' is 200 characters")
    @NotNull(message = "'regularexpression' is required")
    private String regularExpression;

    @JsonIgnore
    @JsonProperty("lastupdate")
    private LocalDateTime lastUpdate;
}
