package co.com.cleanarchitecture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import co.com.cleanarchitecture.model.brand.gateways.BrandRepository;
import co.com.cleanarchitecture.model.category.gateways.CategoryRepository;
import co.com.cleanarchitecture.model.typeidentification.gateways.TypeDocIdentificationRepository;
import co.com.cleanarchitecture.usecase.brand.BrandUseCase;
import co.com.cleanarchitecture.usecase.category.CategoryUseCase;
import co.com.cleanarchitecture.usecase.typedocidentification.TypeDocIdentificationUseCase;
import technicalogs.gateways.LoggerRepository;

@Configuration
@ComponentScan(basePackages = "co.com.cleanarchitecture.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    @Bean
    public CategoryUseCase beanCategoryUseCase(CategoryRepository repository,
                                               LoggerRepository logger) {
        return new CategoryUseCase(repository, logger);
    }

    @Bean
    public TypeDocIdentificationUseCase beanTypeDocIdentificationUseCase(
            TypeDocIdentificationRepository repository, LoggerRepository logger) {
        return new TypeDocIdentificationUseCase(repository, logger);
    }

    @Bean
    public BrandUseCase beanBrandUseCase(
            BrandRepository repository, LoggerRepository logger) {
        return new BrandUseCase(repository, logger);
    }

}
