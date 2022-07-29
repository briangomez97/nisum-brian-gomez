package co.com.nisum.model.dto;

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

    private T body;
    private Integer length = null;
    private String message = null;

    public SuccessDTO(T body, String message) {
        this.body = body;
        this.message = message;
        if (this.body instanceof List) {
            this.length = ((List) this.body).size();
        }
        if (this.body instanceof Map) {
            this.length = ((Map) this.body).size();
        }
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
