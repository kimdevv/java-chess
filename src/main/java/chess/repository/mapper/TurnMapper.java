package chess.repository.mapper;

import chess.domain.piece.Team;

public class TurnMapper {
    public static Team mapToTurn(String value) {
        if (value.equals("WHITE")) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
