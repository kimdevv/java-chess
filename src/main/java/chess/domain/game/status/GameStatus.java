package chess.domain.game.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public interface GameStatus {

    GameStatus move(final Position source, final Position target);

    boolean isFinish();

    Color getTurn();

    Board getBoard();
}
