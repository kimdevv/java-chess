package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class StartState implements GameCommandState {

    private StartState() {
    }

    private static class SingleInstanceHolder {
        private static final StartState INSTANCE = new StartState();
    }

    public static StartState getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    @Override
    public void executeStartCommand(ChessBoard chessBoard) {
    }

    @Override
    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquares) {
        chessGame.executeTurn(moveSquares);
    }

    @Override
    public void executeStatusCommand(ChessBoard chessBoard) {
    }

    public boolean isStartState(GameCommandState gameCommandState) {
        return gameCommandState.equals(this);
    }
}
