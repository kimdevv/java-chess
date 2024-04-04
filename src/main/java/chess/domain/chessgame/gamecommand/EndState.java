package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import java.util.List;

public class EndState implements GameCommandState {

    private EndState() {
    }

    private static class SingleInstanceHolder {
        private static final EndState INSTANCE = new EndState();
    }

    public static EndState getInstance() {
        return EndState.SingleInstanceHolder.INSTANCE;
    }

    @Override
    public void executeStartCommand(ChessBoard chessBoard) {
    }

    @Override
    public void executeMoveCommand(ChessGame chessGame, List<Square> moveSquare) {
        throw new IllegalArgumentException("[ERROR] 게임이 시작되지 않아 움직일 수 없습니다.");
    }

    @Override
    public void executeStatusCommand(ChessBoard chessBoard) {
        throw new IllegalArgumentException("[ERROR] 게임이 시작되지 않아 현황을 볼 수 없습니다.");
    }

    public boolean isEndState(GameCommandState gameCommandState) {
        return gameCommandState.equals(this);
    }
}
