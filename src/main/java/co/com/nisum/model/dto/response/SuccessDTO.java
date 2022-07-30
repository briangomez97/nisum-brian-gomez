package co.com.nisum.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessDTO<T> implements Serializable {

    private Integer length;
    private String message;
    private T body;

    public SuccessDTO(String message) {
        this.message = message;
    }

    public SuccessDTO(T body) {
        this.body = body;
        if (this.body instanceof List) {
            this.length = ((List) this.body).size();
        }
        if (this.body instanceof Map) {
            this.length = ((Map) this.body).size();
        }
    }
}
