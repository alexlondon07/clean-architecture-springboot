package co.com.cleanarchitecture.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateKeyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException() {
        super("Duplicate Key ");
    }
}