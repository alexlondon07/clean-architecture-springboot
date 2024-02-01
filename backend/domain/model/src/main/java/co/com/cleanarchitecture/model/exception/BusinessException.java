package co.com.cleanarchitecture.model.exception;

import co.com.cleanarchitecture.model.exception.message.BusinessErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends Exception {

    private final BusinessErrorMessage businessErrorMessage;
}
