package state.chessGame;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import java.util.Map;
import state.chessGame.base.NotRunningGameState;

public class InitialChessGameState extends NotRunningGameState {

    public InitialChessGameState() {
    }

    @Override
    public Map<Coordinate, ChessPiece> getBoard() {
        throw new IllegalArgumentException("아직 시작하지 않아 보드판을 가져올 수 없습니다.");
    }

    @Override
    public boolean isKingCaught() {
        return false;
    }

    @Override
    public void show() {
        throw new IllegalArgumentException("아직 시작하지 않아 보여줄 상태가 없습니다.");
    }

    @Override
    public Long getGameId() {
        throw new UnsupportedOperationException("초기의 상태에서는 게임의 id를 불러올 수 없습니다.");
    }
}
