package co.com.cleanarchitecture.model.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsModel {
    public static final String A_SYSTEM_FAILURE_OCCURRED =
            "Ocurri� una falla en el sistema,"
                    + " estamos trabajando"
                    + " para ofrecerte una soluci�n lo m�s pronto posible. Si el error persiste comun�cate con el administrador.";
}