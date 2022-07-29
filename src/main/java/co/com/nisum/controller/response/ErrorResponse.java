package co.com.nisum.controller.response;

import co.com.nisum.model.dto.ErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private ErrorDTO error;

    public ErrorResponse(String message, String exceptionName) {
        error = new ErrorDTO(message, exceptionName);
    }
}