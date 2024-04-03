package chess.dao;

import chess.dto.TurnDto;

public interface TurnDao {

    TurnDto findOne();

    void update(final TurnDto turnDto);

    void deleteAll();
}
