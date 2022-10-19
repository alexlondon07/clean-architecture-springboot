package co.com.cleanarchitecture.usecase.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.com.cleanarchitecture.model.category.Category;
import co.com.cleanarchitecture.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;


@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryRepository repository;
    private final LoggerRepository logger;

    public Category saveCategory(Category category) {
        try {
            logger.info("Saving category");
            return repository.saveCategory(category);
        } catch (Exception ex) {
            logger.error("Error saving category  -->", ex);
            return null;
        }
    }

    public Category updateCategory(Category category) {
        try {
            logger.info("Updating category");
            return repository.saveCategory(category);
        } catch (Exception ex) {
            logger.error("Error updating category  -->", ex);
            return null;
        }
    }

    public List<Category> getCategories() {
        List<Category> list = new ArrayList<>();
        try {
            list = repository.getAll();
            logger.info("Getting categories list");
        } catch (Exception ex) {
            logger.error("Error Getting categories list -->", ex);
            return Collections.emptyList();
        }
        return list;
    }


    public Category getById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.delete(id);
    }

}


