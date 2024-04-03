package chess.dto;

import chess.domain.position.Move;
import chess.domain.position.Position;

public record MoveResponse(long id, String source, String target) {
    public static Move from(MoveResponse moveResponse) {
        Position source = Position.convert(moveResponse.source);
        Position target = Position.convert(moveResponse.target);

        return new Move(source, target);
    }
}
