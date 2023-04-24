package co.com.cleanarchitecture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;

import co.com.cleanarchitecture.api.security.services.UserDetailsServiceImpl;
import co.com.cleanarchitecture.model.brand.gateways.BrandRepository;
import co.com.cleanarchitecture.model.category.gateways.CategoryRepository;
import co.com.cleanarchitecture.model.role.gateways.RoleRepository;
import co.com.cleanarchitecture.model.typeidentification.gateways.TypeDocIdentificationRepository;
import co.com.cleanarchitecture.model.user.gateways.UserRepository;
import co.com.cleanarchitecture.usecase.brand.BrandUseCase;
import co.com.cleanarchitecture.usecase.category.CategoryUseCase;
import co.com.cleanarchitecture.usecase.role.RoleUseCase;
import co.com.cleanarchitecture.usecase.typedocidentification.TypeDocIdentificationUseCase;
import co.com.cleanarchitecture.usecase.user.UserUseCase;
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
    public BrandUseCase beanBrandUseCase(BrandRepository repository, LoggerRepository logger) {
        return new BrandUseCase(repository, logger);
    }

    @Bean
    @Primary
    public UserUseCase beanUserUseCase(UserRepository repository, LoggerRepository logger) {
        return new UserUseCase(repository, logger);
    }

    @Bean
    @Primary
    public RoleUseCase beanRoleUseCase(RoleRepository repository, LoggerRepository logger) {
        return new RoleUseCase(repository, logger);
    }

    @Bean
    public UserDetailsServiceImpl beanUserDetailsServiceImplUseCase(UserUseCase repository) {
        return new UserDetailsServiceImpl(repository);
    }

}
