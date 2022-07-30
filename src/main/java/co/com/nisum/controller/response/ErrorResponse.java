package co.com.nisum.controller.response;

import co.com.nisum.model.dto.response.ErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private ErrorDTO error;
    private Integer status;

    public ErrorResponse(String message, String exceptionName, Integer status) {
        error = new ErrorDTO(message, exceptionName);
        this.status = status;
    }
}