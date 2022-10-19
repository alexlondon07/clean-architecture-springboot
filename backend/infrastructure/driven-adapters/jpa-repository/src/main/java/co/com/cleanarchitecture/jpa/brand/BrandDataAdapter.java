package co.com.cleanarchitecture.jpa.brand;


import java.util.List;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.brand.Brand;
import co.com.cleanarchitecture.model.brand.gateways.BrandRepository;

@Repository
public class BrandDataAdapter extends AdapterOperations<
        Brand, BrandData, Long,
        BrandDataRepository> implements BrandRepository {

    public BrandDataAdapter(BrandDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Brand.class));
    }

    @Override
    public Brand saveBrand(Brand data) {
        return super.save(data);
    }

    @Override
    public List<Brand> getAll() {
        return super.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Brand findById(Long id) {
        return super.findById(id);
    }
}
