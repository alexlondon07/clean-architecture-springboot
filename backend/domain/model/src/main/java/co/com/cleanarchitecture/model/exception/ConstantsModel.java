package co.com.cleanarchitecture.model.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsModel {
    public static final String A_SYSTEM_FAILURE_OCCURRED =
            "Ocurrió una falla en el sistema,"
                    + " estamos trabajando"
                    + " para ofrecerte una solución lo más pronto posible. Si el error persiste comunícate con el administrador.";
}