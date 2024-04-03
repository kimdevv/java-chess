package chess.dto;

import chess.domain.position.Position;

public record MoveRequest(String source, String target) {
    public static MoveRequest of(Position source, Position target) {
        return new MoveRequest(source.getValue(), target.getValue());
    }
}
