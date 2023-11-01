package co.com.cleanarchitecture.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Utility {

    public static ResponseEntity<?> validateRequest(BindingResult ex) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> errors = new ArrayList<>();
        for (FieldError violation : ex.getFieldErrors()) {
            Map<String, String> transformedError = new HashMap<>();
            String fieldName = violation.getField();
            transformedError.put("error", "El campo " +
                    fieldName.substring(fieldName.lastIndexOf('.') + 1) + " " +
                    violation.getDefaultMessage());
            errors.add(transformedError);
        }
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
