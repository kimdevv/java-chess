package state.chessGame;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import java.util.Map;
import state.chessGame.base.NotRunningGameState;
import view.OutputView;

public class ChessGameStateEnd extends NotRunningGameState {

    private final Long gameId;

    public ChessGameStateEnd(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public Map<Coordinate, ChessPiece> getBoard() {
        throw new UnsupportedOperationException("이미 종료한 상태의 게임으로 보드판을 불러올 수 없습니다.");
    }

    @Override
    public boolean isKingCaught() {
        return false;
    }

    @Override
    public Long getGameId() {
        return gameId;
    }

    @Override
    public void show() {
        OutputView.printEndGuide();
    }
}
