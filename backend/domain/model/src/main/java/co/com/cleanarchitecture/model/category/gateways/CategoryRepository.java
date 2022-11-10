package co.com.cleanarchitecture.model.category.gateways;

import java.util.List;

import co.com.cleanarchitecture.model.category.Category;

public interface CategoryRepository {

    Category saveCategory(Category category);

    List<Category> getAll();

    void delete(Long id);

    Category findById(Long id);

    void enable(Long id, Boolean enable);
}
