package app.platform.userservice.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new Error(HttpStatus.BAD_REQUEST, error));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new Error(HttpStatus.BAD_REQUEST, error));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return buildResponseEntity(new Error(HttpStatus.BAD_REQUEST, errors.toString()));
    }

    private ResponseEntity<Object> buildResponseEntity(Error error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExists(
            UserAlreadyExistsException ex) {
        Error error = new Error();
        error.setStatus(HttpStatus.CONFLICT);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(
            UserNotFoundException ex) {
        Error error = new Error();
        error.setStatus(NOT_FOUND);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }
    @ExceptionHandler(TokenExpiredException.class)
    protected ResponseEntity<Object> handleUTokenExpired(
            TokenExpiredException ex) {
        Error error = new Error();
        error.setStatus(UNAUTHORIZED);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

}
