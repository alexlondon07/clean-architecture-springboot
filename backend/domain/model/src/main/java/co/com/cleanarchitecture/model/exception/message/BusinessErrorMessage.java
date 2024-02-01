package co.com.cleanarchitecture.model.exception.message;

import static co.com.cleanarchitecture.model.exception.ConstantsModel.A_SYSTEM_FAILURE_OCCURRED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage {

    AUTHENTICATION_NOT_FOUND("ASB0011", "Authentication not found", "471", A_SYSTEM_FAILURE_OCCURRED);

    private final String code;
    private final String description;
    private final String itcCode;
    private final String message;
}