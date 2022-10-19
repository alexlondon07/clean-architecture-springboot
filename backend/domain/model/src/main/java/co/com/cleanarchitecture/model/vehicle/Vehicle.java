package co.com.cleanarchitecture.model.vehicle;

import co.com.cleanarchitecture.model.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Vehicle {
    private String classVehicle;
    private Brand brand;
    private String bodywork;
    private String cylinderCapacity;
    private String capacity;
    private String armored;
    private String imported;
    private String model;
    private String doors;
    private String box;
    private String combustion;
}