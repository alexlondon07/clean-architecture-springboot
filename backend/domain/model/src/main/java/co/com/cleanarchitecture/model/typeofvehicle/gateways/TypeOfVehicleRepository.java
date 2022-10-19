package co.com.cleanarchitecture.model.typeofvehicle.gateways;

import java.util.List;

import co.com.cleanarchitecture.model.typeofvehicle.TypeOfVehicle;

public interface TypeOfVehicleRepository {

    TypeOfVehicle saveBrand(TypeOfVehicle data);

    List<TypeOfVehicle> getAll();

    void delete(Long id);

    TypeOfVehicle findById(Long id);
}
