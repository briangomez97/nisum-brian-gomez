package co.com.nisum.controller.handler;

import co.com.nisum.controller.response.ErrorResponse;
import co.com.nisum.controller.response.SuccessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class SuccessfulResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)) {
            if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseBinding.class) == false) {
                if ((!(o instanceof ErrorResponse)) && (!(o instanceof SuccessResponse))) {
                    SuccessResponse<Object> responseBody = new SuccessResponse<>(o, HttpStatus.OK.value());
                    return responseBody;
                } else if(o instanceof SuccessResponse) {
                    serverHttpResponse.setStatusCode(HttpStatus.valueOf(((SuccessResponse) o).getStatus()));
                }
            }
        }
        return o;
    }
}
