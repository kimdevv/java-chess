package db;

import java.util.List;

public interface MovementDao {

    void createMovement(Movement movement);

    List<Movement> findAll();

    void deleteAll();

}
