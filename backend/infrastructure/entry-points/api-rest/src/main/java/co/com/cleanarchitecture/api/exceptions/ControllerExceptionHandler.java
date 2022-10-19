package co.com.cleanarchitecture.api.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(MissingDataException ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    public static ErrorMessage getErrorMessage(WebRequest request, HttpStatus httpStatusValue,
                                               String exceptionMessage) {
        return new ErrorMessage(
                httpStatusValue.value(),
                new Date(),
                exceptionMessage,
                request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Map<String, Object> handleValidationError(ConstraintViolationException exception) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            Map<String, String> transformedError = new HashMap<>();

            String fieldName = violation.getPropertyPath().toString();
            transformedError.put("field", fieldName.substring(fieldName.lastIndexOf('.') + 1));
            transformedError.put("error", violation.getMessage());

            errors.add(transformedError);
        }
        response.put("errors", errors);
        return response;
    }
}