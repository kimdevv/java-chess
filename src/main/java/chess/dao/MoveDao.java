package chess.dao;

import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import java.util.List;

public interface MoveDao {
    void save(MoveRequest moveRequest);

    List<MoveResponse> findAll();

    void deleteAll();
}
