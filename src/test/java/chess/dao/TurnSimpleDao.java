package chess.dao;

import chess.domain.piece.Team;
import chess.dto.TurnDto;

import java.util.ArrayList;
import java.util.List;

public class TurnSimpleDao implements TurnDao {
    private List<Team> turns = new ArrayList<>();

    @Override
    public TurnDto findOne() {
        return TurnDto.from(turns.get(0));
    }

    @Override
    public void update(final TurnDto turnDto) {
        final Team turn = turnDto.getTurn();
        if (turns.isEmpty()) {
            turns.add(turn);
            return;
        }
        turns.set(0, turn);
    }

    @Override
    public void deleteAll() {
        turns.clear();
    }
}
