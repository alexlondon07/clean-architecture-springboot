package co.com.bancolombia.apiservice;

import co.com.bancolombia.model.person.Person;
import co.com.bancolombia.model.person.gateways.PersonService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ApiService implements PersonService {
    @Override
    public Mono<Person> getPerson(String id) {
        return Mono.just(Person.builder().id(id).balance(1000000f).build());
    }

    @Override
    public Mono<Float> getBalance(String id) {
        return Mono.just(100000f);
    }
}
