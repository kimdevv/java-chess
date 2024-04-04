package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.gamecommand.GameCommandExecutor;
import chess.domain.chessgame.gamecommand.GameCommandState;
import chess.domain.chessgame.gamecommand.GameElements;
import chess.domain.chesspiece.Camp;
import java.util.List;

public class ChessGame {

    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void executeGame(GameCommandState gameCommandState, GameElements gameElements) {
        GameCommandExecutor gameCommandExecutor = new GameCommandExecutor(gameCommandState);
        gameCommandExecutor.execute(chessBoard, gameElements);
    }

    public void executeTurn(List<Square> moveSquares) {
        Square moveSource = moveSquares.get(MOVE_SOURCE_SQUARE_INDEX);
        Square target = moveSquares.get(TARGET_SQUARE_INDEX);
        chessBoard.move(moveSource, target);
    }

    public Camp findCampKingAlive() {
        return chessBoard.campKingAlive();
    }

    public boolean isGameFinished() {
        return chessBoard.isKingDead();
    }
}
