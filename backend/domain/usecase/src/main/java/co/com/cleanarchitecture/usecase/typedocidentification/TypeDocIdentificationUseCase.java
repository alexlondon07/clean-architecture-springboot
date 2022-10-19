package co.com.cleanarchitecture.usecase.typedocidentification;

import java.util.Collections;
import java.util.List;

import co.com.cleanarchitecture.model.typeidentification.TypeDocIdentification;
import co.com.cleanarchitecture.model.typeidentification.gateways.TypeDocIdentificationRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

@RequiredArgsConstructor
public class TypeDocIdentificationUseCase {

    private final TypeDocIdentificationRepository repository;
    private final LoggerRepository logger;

    public TypeDocIdentification save(TypeDocIdentification data) {
        try {
            logger.info("Saving TypeDocIdentification");
            return repository.save(data);
        } catch (Exception ex) {
            logger.error("Error saving TypeDocIdentification  -->", ex);
            return null;
        }
    }

    public TypeDocIdentification updateTypeDocIdentification(TypeDocIdentification data) {
        try {
            logger.info("Updating TypeDocIdentification");
            return repository.save(data);
        } catch (Exception ex) {
            logger.error("Error updating TypeDocIdentification  -->", ex);
            return null;
        }
    }

    public List<TypeDocIdentification> getTypeDocIdentifications() {
        List<TypeDocIdentification> list;
        try {
            list = repository.getAll();
            logger.info("Getting TypeDocIdentification list");
        } catch (Exception ex) {
            logger.error("Error Getting TypeDocIdentification list -->", ex);
            return Collections.emptyList();
        }
        return list;
    }
    
    public TypeDocIdentification getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.delete(id);
    }
}
