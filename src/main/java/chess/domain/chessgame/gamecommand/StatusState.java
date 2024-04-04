package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class StatusState implements GameCommandState {

    private StatusState() {
    }

    private static class SingleInstanceHolder {
        private static final StatusState INSTANCE = new StatusState();
    }

    public static StatusState getInstance() {
        return StatusState.SingleInstanceHolder.INSTANCE;
    }

    @Override
    public void executeStartCommand(ChessBoard chessBoard) {
        throw new IllegalArgumentException("[ERROR] 이미 시작되었습니다.");
    }

    @Override
    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquares) {
        chessGame.executeTurn(moveSquares);
    }

    @Override
    public void executeStatusCommand(ChessBoard chessBoard) {
    }

    public boolean isStatusState(GameCommandState gameCommandState) {
        return gameCommandState.equals(this);
    }
}
