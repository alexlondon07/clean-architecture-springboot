package co.com.cleanarchitecture.jpa.position;

import co.com.cleanarchitecture.jpa.helper.AdapterOperations;
import co.com.cleanarchitecture.jpa.helper.AdapterOperationsSimple;
import co.com.cleanarchitecture.model.position.Position;
import co.com.cleanarchitecture.model.position.gateways.PositionRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDataAdapter extends AdapterOperationsSimple<
        Position, PositionData, Long, PositionDataRepository>
        implements PositionRepository {

    public PositionDataAdapter(PositionDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, positionData -> mapper.map(positionData, Position.class));
    }
    @Override
    public List<Position> getPositionsAll() {
        return super.findAll();
    }
}
