package co.com.nisum.controller.response;

import co.com.nisum.model.dto.SuccessDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {

    private SuccessDTO<T> response;
    private Integer status;

    public SuccessResponse(T object, Integer status) {
        this.response = new SuccessDTO<>(object);
        this.status = status;
    }

    public SuccessResponse(T object, String message, Integer status) {
        this.response = new SuccessDTO<>(object, message);
        this.status = status;
    }

}
