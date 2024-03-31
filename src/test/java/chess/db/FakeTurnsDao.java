package chess.db;

import chess.dto.TurnDto;
import java.util.ArrayList;
import java.util.List;

public class FakeTurnsDao implements TurnsDao {
    private final List<TurnDto> turns = new ArrayList<>();

    @Override
    public void create(TurnDto turnDto) {
        turns.add(turnDto);
    }

    @Override
    public TurnDto find() {
        return turns.get(0);
    }

    @Override
    public void deleteAll() {
        turns.clear();
    }
}
