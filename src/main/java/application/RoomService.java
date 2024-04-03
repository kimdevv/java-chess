package application;

import domain.room.Room;
import java.util.List;
import java.util.Optional;
import persistence.RoomDao;

public class RoomService {
    private final RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void save(String name) {
        Room room = new Room(name);
        roomDao.save(room);
    }

    public List<Room> findAll() {
        return roomDao.findAll();
    }

    public void deleteById(int roomId) {
        validateRoomId(roomId);
        roomDao.deleteById(roomId);
    }

    public void validateRoomId(int roomId) {
        Optional<Room> optionalRoom = roomDao.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 룸입니다.");
        }
    }
}
