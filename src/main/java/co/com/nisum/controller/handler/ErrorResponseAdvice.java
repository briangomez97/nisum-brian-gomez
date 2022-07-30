package co.com.nisum.controller.handler;

import co.com.nisum.controller.response.ErrorResponse;
import co.com.nisum.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
    private static final ConcurrentHashMap<String, Integer> CONTROLLED_ERRORS = new ConcurrentHashMap<>();
    private static final String EXCEPTION_VALIDATION_NAME= MethodArgumentNotValidException.class.getSimpleName();

    public ErrorResponseAdvice() {
        CONTROLLED_ERRORS.put(EXCEPTION_VALIDATION_NAME, HttpStatus.BAD_REQUEST.value());
        CONTROLLED_ERRORS.put(UnauthorizedUserException.class.getSimpleName(), HttpStatus.UNAUTHORIZED.value());
        CONTROLLED_ERRORS.put(DisabledException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CONTROLLED_ERRORS.put(BadCredentialsException.class.getSimpleName(), HttpStatus.UNAUTHORIZED.value());
        CONTROLLED_ERRORS.put(UserNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        CONTROLLED_ERRORS.put(InternalAuthenticationServiceException.class.getSimpleName(), HttpStatus.UNAUTHORIZED.value());
        CONTROLLED_ERRORS.put(DuplicateEmailException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CONTROLLED_ERRORS.put(RegularExpressionNotExistException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {
        ResponseEntity<ErrorResponse> result;
        String exceptionName = exception.getClass().getSimpleName();
        String message = exception.getMessage();
        Integer code = CONTROLLED_ERRORS.get(exceptionName);
        if (code != null) {
            log.warn(exceptionName, exception);
            ErrorResponse error = new ErrorResponse(message, exceptionName, code);
            processIfValidationException(exception, exceptionName, error);
            result = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            log.error(exceptionName, exception);
            ErrorResponse error = new ErrorResponse(AN_ERROR_OCCURRED, exceptionName, HttpStatus.INTERNAL_SERVER_ERROR.value());
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

