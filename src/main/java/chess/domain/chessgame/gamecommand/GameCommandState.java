package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public interface GameCommandState {

    void executeStartCommand(ChessBoard chessBoard);

    void executeMoveCommand(ChessGame chessGame, List<Square> moveSquare);

    void executeStatusCommand(ChessBoard chessBoard);
}
