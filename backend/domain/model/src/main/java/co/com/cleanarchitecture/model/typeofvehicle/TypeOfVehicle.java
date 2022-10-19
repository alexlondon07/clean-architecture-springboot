package co.com.cleanarchitecture.model.typeofvehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TypeOfVehicle  {

    private Long id;

    private String name;

    private String code;

    private boolean enable;
}
