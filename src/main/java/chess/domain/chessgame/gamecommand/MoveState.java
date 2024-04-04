package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class MoveState implements GameCommandState {

    private MoveState() {
    }

    private static class SingleInstanceHolder {
        private static final MoveState INSTANCE = new MoveState();
    }

    public static MoveState getInstance() {
        return MoveState.SingleInstanceHolder.INSTANCE;
    }

    @Override
    public void executeStartCommand(ChessBoard chessBoard) {
        throw new IllegalArgumentException();
    }

    @Override
    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquares) {
        chessGame.executeTurn(moveSquares);
    }

    @Override
    public void executeStatusCommand(ChessBoard chessBoard) {
    }

    public boolean isMoveState(GameCommandState gameCommandState) {
        return gameCommandState.equals(this);
    }
}
