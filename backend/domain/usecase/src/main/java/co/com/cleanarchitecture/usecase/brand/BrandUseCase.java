package co.com.cleanarchitecture.usecase.brand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.com.cleanarchitecture.model.brand.Brand;
import co.com.cleanarchitecture.model.brand.gateways.BrandRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RequiredArgsConstructor
public class BrandUseCase {

    private final BrandRepository repository;
    private final LoggerRepository logger;

    public Brand save(Brand data) {
        try {
            logger.info("Saving");
            return repository.saveBrand(data);
        } catch (Exception ex) {
            logger.error("Error saving brand-->", ex);
            return null;
        }
    }

    public Brand update(Brand data) {
        try {
            logger.info("Updating");
            return repository.saveBrand(data);
        } catch (Exception ex) {
            logger.error("Error updating brand-->", ex);
            return null;
        }
    }

    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();
        try {
            list = repository.getAll();
            logger.info("Getting list");
        } catch (Exception ex) {
            logger.error("Error Getting brand list -->", ex);
            return Collections.emptyList();
        }
        return list;
    }

    public Brand getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.delete(id);
    }
}
