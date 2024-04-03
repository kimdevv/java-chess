package persistence;

import dto.MovementDto;
import java.util.List;

public interface BoardDao {
    void save(MovementDto movementDto, int roomId);

    List<MovementDto> findByRoomId(int roomId);

    void deleteByRoomId(int roomId);
}
