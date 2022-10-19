package co.com.cleanarchitecture.jpa.typeidentifications;


import java.util.List;
import java.util.function.Function;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.typeidentification.TypeDocIdentification;
import co.com.cleanarchitecture.model.typeidentification.gateways.TypeDocIdentificationRepository;

@Repository
public class TypeDocIdentificationsDataAdapter extends AdapterOperations<TypeDocIdentification,
        TypeDocIdentificationsData, Long, TypeDocIdentificationsDataRepository>
        implements TypeDocIdentificationRepository {

    public TypeDocIdentificationsDataAdapter(TypeDocIdentificationsDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, TypeDocIdentification.class));
    }

    @Override
    public List<TypeDocIdentification> getAll() {
        return super.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TypeDocIdentification findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Page<TypeDocIdentification> findAllPageable(Pageable pageable) {
        return super.findAllPageable(pageable);
    }

    @Override
    public TypeDocIdentification save(TypeDocIdentification data) {
        return super.save(data);
    }
}


