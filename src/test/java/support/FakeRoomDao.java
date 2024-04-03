package support;

import domain.room.Room;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import persistence.RoomDao;

public class FakeRoomDao implements RoomDao {
    private final Map<Integer, Room> rooms;
    private int roomId;

    public FakeRoomDao() {
        this.rooms = new HashMap<>();
        this.roomId = 0;
    }

    @Override
    public void save(Room room) {
        rooms.put(roomId++, room);
    }

    @Override
    public List<Room> findAll() {
        return List.copyOf(rooms.values());
    }

    @Override
    public Optional<Room> findById(int id) {
        if (rooms.containsKey(id)) {
            return Optional.of(rooms.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int roomId) {
        rooms.remove(roomId);
    }
}
