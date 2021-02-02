package co.com.bancolombia.usecase.person;

import co.com.bancolombia.model.person.Person;
import co.com.bancolombia.model.person.gateways.PersonService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonUseCase {

    private  final PersonService personServiceGateway;

    public Mono<Person> getPerson(String id){
        return personServiceGateway.getPerson(id);
    }

    public Mono<Float> getBalance(String id){
        return personServiceGateway.getBalance(id);
    }

}
