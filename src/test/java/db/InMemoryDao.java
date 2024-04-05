package db;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDao implements MovementDao {
    private final List<Movement> movements = new ArrayList<>();

    @Override
    public void createMovement(final Movement movement) {
        movements.add(movement);
    }

    @Override
    public List<Movement> findAll() {
        return movements;
    }

    public void deleteAll() {
        movements.clear();
    }
}
