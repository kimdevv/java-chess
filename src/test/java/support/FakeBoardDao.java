package support;

import dto.MovementDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistence.BoardDao;

public class FakeBoardDao implements BoardDao {
    private final Map<Integer, List<MovementDto>> movements;

    public FakeBoardDao() {
        this(new HashMap<>());
    }

    public FakeBoardDao(int roomId, List<MovementDto> movementDtos) {
        this(Map.of(roomId, new ArrayList<>(movementDtos)));
    }

    private FakeBoardDao(Map<Integer, List<MovementDto>> movements) {
        this.movements = new HashMap<>(movements);
    }

    @Override
    public void save(MovementDto movementDto, int roomId) {
        List<MovementDto> movementDtos = movements.getOrDefault(roomId, new ArrayList<>());
        movementDtos.add(movementDto);
        movements.put(roomId, movementDtos);
    }

    @Override
    public List<MovementDto> findByRoomId(int roomId) {
        return movements.getOrDefault(roomId, Collections.emptyList());
    }

    @Override
    public void deleteByRoomId(int roomId) {
        movements.remove(roomId);
    }
}
