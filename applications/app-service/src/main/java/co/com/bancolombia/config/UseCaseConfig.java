package co.com.bancolombia.config;

import co.com.bancolombia.model.person.gateways.PersonService;
import co.com.bancolombia.usecase.person.PersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PersonUseCase getPersonUseCase( PersonService serviceGateWay){
        return new PersonUseCase( serviceGateWay );
    }
}
