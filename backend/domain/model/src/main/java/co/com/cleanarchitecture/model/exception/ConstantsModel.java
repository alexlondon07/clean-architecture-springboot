package co.com.cleanarchitecture.model.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsModel {
    public static final String A_SYSTEM_FAILURE_OCCURRED =
            "Hay una falla en el sistema,"
                    + " estamos trabajando"
                    + " para ofrecerte una solucion muy pronta. Si el error persiste comunicate con el " +
                    "administrador.";
}