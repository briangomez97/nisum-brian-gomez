package co.com.nisum.controller.advice;

import co.com.nisum.controller.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ErrorResponseAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResponseAdvice.class);
    private static final String AN_ERROR_OCCURRED = "An error occurred, please contact the administrator";
    private static final ConcurrentHashMap<String, Integer> HTTP_CODES = new ConcurrentHashMap<>();

    public ErrorResponseAdvice() {
        //en caso de tener otra excepcion matricularla aca
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {
        ResponseEntity<ErrorResponse> resultado;

        String exceptionName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = HTTP_CODES.get(exceptionName);

        if (code != null) {
            ErrorResponse error = new ErrorResponse(message, exceptionName);
            resultado = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            LOGGER.error(exceptionName, exception);
            ErrorResponse error = new ErrorResponse(AN_ERROR_OCCURRED, exceptionName);
            resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultado;
    }
}

