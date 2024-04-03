package persistence;

import domain.room.Room;
import java.util.List;
import java.util.Optional;

public interface RoomDao {
    void save(Room room);

    List<Room> findAll();

    Optional<Room> findById(int id);

    void deleteById(int roomId);
}
