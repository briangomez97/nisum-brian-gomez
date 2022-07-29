package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO implements Serializable {

    private String exceptionName;
    private String message;

    public ErrorDTO(String message, String exceptionName) {
        this.message = message;
        this.exceptionName = exceptionName;
    }
}
