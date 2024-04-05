package co.com.cleanarchitecture.usecase.position;


import co.com.cleanarchitecture.model.position.Position;
import co.com.cleanarchitecture.model.position.gateways.PositionRepository;
import lombok.RequiredArgsConstructor;
import technicalogs.gateways.LoggerRepository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class PositionUseCase {
    private final PositionRepository repository;

    private final LoggerRepository logger;

    public List<Position> getAll(){
        try {
            return repository.getPositionsAll();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
