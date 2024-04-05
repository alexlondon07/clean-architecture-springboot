package co.com.cleanarchitecture.model.position.gateways;

import co.com.cleanarchitecture.model.position.Position;

import java.util.List;

public interface PositionRepository {
    List<Position> getPositionsAll();
}
