package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class GameCommandExecutor {

    private final GameCommandState gameCommandState;

    public GameCommandExecutor(GameCommandState gameCommandState) {
        this.gameCommandState = gameCommandState;
    }

    public void executeStartCommand(ChessBoard chessBoard) {
        gameCommandState.executeStartCommand(chessBoard);
    }

    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquare) {
        gameCommandState.executeMoveCommand(chessGame, moveSquare);
    }

    public void executeStatusCommand(ChessBoard chessBoard) {
        gameCommandState.executeStatusCommand(chessBoard);
    }

    public void execute(ChessBoard chessBoard, GameElements gameElements) {
        if (StartState.getInstance().isStartState(gameCommandState)) {
            executeStartCommand(chessBoard);
        }
        if (MoveState.getInstance().isMoveState(gameCommandState)) {
            ChessGame chessGame = new ChessGame(chessBoard);
            List<Square> moveSquares = gameElements.createMoveSquare();
            executeMoveCommand(chessGame, moveSquares);
        }
        if (StatusState.getInstance().isStatusState(gameCommandState)) {
            executeStatusCommand(chessBoard);
        }
    }
}
