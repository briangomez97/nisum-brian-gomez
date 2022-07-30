package co.com.nisum.controller.response;

import co.com.nisum.model.dto.response.SuccessDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> {

    private SuccessDTO<T> response;
    private Integer status;

    public SuccessResponse(T object, Integer status) {
        this.response = new SuccessDTO<>(object);
        this.status = status;
    }

    public SuccessResponse(String message, Integer status) {
        this.response = new SuccessDTO<>(message);
        this.status = status;
    }

}
