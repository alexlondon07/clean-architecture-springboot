package co.com.cleanarchitecture.model.brand.gateways;

import java.util.List;

import co.com.cleanarchitecture.model.brand.Brand;

public interface BrandRepository {

    Brand saveBrand(Brand data);

    List<Brand> getAll();

    void delete(Long id);

    Brand findById(Long id);
}
