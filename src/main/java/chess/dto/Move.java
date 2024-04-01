package chess.dto;

import chess.domain.position.Position;

public record Move(Position source, Position target) {
}
