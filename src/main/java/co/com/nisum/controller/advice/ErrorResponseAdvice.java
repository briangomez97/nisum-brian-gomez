package co.com.nisum.controller.advice;

import co.com.nisum.controller.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ControllerAdvice
public class ErrorResponseAdvice {

    private static final String AN_ERROR_OCCURRED = "An error occurred, please contact the administrator";
    private static final ConcurrentHashMap<String, Integer> HTTP_CODES = new ConcurrentHashMap<>();
    private static final String EXCEPTION_VALIDATION_NAME= MethodArgumentNotValidException.class.getSimpleName();

    public ErrorResponseAdvice() {
        HTTP_CODES.put(EXCEPTION_VALIDATION_NAME, HttpStatus.BAD_REQUEST.value());
        //en caso de tener otra excepcion matricularla aca
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {
        ResponseEntity<ErrorResponse> result;
        String exceptionName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = HTTP_CODES.get(exceptionName);
        if (code != null) {
            log.warn(exceptionName, exception);
            ErrorResponse error = new ErrorResponse(message, exceptionName);
            processIfValidationException(exception, exceptionName, error);
            result = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            log.error(exceptionName, exception);
            ErrorResponse error = new ErrorResponse(AN_ERROR_OCCURRED, exceptionName);
            result = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private void processIfValidationException(Exception exception, String exceptionName, ErrorResponse error) {
        if(exceptionName.equals(EXCEPTION_VALIDATION_NAME) && exception instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) exception).getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            processFieldErrors(fieldErrors, error);
        }
    }

    private void processFieldErrors(List<FieldError> fieldErrors, ErrorResponse errorResponse) {
        errorResponse.getError().setFieldErrors(new ArrayList<>());
        for (FieldError fieldError: fieldErrors) {
            errorResponse.getError().addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        errorResponse.getError().setMessage(null);
    }
}

