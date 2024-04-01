package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public record ChessDTO(Board board, Color color) {
}
