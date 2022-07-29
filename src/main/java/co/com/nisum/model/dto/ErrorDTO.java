package co.com.nisum.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO implements Serializable {

    private String exceptionName;
    private String message;
    private List<Fields> fieldErrors;

    public ErrorDTO(String message, String exceptionName) {
        this.message = message;
        this.exceptionName = exceptionName;
    }

    public void addFieldError(String field, String message) {
        Fields error = new Fields(field, message);
        fieldErrors.add(error);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    protected class Fields {
        private String field;
        private String message;
    }
}
