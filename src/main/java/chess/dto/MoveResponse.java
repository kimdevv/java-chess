package chess.dto;

import chess.domain.position.Position;

public record MoveResponse(Long id, String source, String target, Long chessGameId) {

    public static Move from(MoveResponse moveResponse) {
        Position source = Position.from(moveResponse.source());
        Position target = Position.from(moveResponse.target());

        return new Move(source, target);
    }
}
