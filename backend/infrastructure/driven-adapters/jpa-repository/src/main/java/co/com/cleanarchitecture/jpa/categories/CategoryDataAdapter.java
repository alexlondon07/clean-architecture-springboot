package co.com.cleanarchitecture.jpa.categories;

import java.util.List;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.model.category.Category;
import co.com.cleanarchitecture.model.category.gateways.CategoryRepository;

@Repository
public class CategoryDataAdapter extends AdapterOperations< Category, CategoryData, Long,
        CategoryDataRepository> implements CategoryRepository {

    public CategoryDataAdapter(CategoryDataRepository repository, ObjectMapper mapper) {        /**
     *  Could be use mapper.mapBuilder if your domain model implement builder pattern
     *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
     *  Or using mapper.map with the class of the object model
     */
        super(repository, mapper, d -> mapper.map(d, Category.class));
    }

    @Override
    public Page<Category> findAllPageable(Pageable pageable) {
        return super.findAllPageableOrderByIdDesc(pageable);
    }

    @Override
    public Category saveCategory(Category category) {
        return super.save(category);
    }

    @Override
    public List<Category> getAll() {
        return super.findAllByOrderByField("desc", "id");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Category findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void enable(Long id, Boolean enable) {
        repository.enable(id, enable);
    }

}
