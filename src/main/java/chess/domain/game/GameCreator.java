package chess.domain.game;

import chess.domain.board.BoardStatus;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface GameCreator {

    Map<Position, Piece> createBoard();

    BoardStatus createBoardStatus();
}
