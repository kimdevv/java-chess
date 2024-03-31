package chess.db;

import chess.dto.TurnDto;

public interface TurnsDao {

    void create(TurnDto turnDto);

    TurnDto find();

    void deleteAll();
}
