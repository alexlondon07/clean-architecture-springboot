package co.com.cleanarchitecture.api.util;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Utility {

    public Utility() {
    }

    public static ResponseEntity<?> validateRequest(BindingResult ex) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();
        for (FieldError violation : ex.getFieldErrors()) {
            Map<String, String> transformedError = new HashMap<>();
            String fieldName = violation.getField();
            transformedError.put("error", " The field " +
                    fieldName.substring(fieldName.lastIndexOf('.') + 1) + " " +
                    violation.getDefaultMessage());
            errors.add(transformedError);
        }
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public static void getResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Utility.validateRequest(bindingResult);
        }
    }
}
