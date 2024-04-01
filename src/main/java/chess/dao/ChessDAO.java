package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface ChessDAO {
    Map<Position, Piece> getBoard();

    Color getCurrentTurnColor();

    void updateBoard(final Board board);

    void updateColor(final Color color);

    void initialize();
}
