package co.com.cleanarchitecture.model.typeidentification.gateways;

import java.util.List;

import co.com.cleanarchitecture.model.typeidentification.TypeDocIdentification;

public interface TypeDocIdentificationRepository {
    TypeDocIdentification save(TypeDocIdentification data);

    List<TypeDocIdentification> getAll();

    void delete(Long id);

    TypeDocIdentification findById(Long id);
}
