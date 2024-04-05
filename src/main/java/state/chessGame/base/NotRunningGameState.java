package state.chessGame.base;

import domain.coordinate.Coordinate;
import domain.piece.Color;

public abstract class NotRunningGameState implements ChessGameState {

    @Override
    public ChessGameState move(Coordinate start, Coordinate destination) {
        throw new UnsupportedOperationException("움직일 수 없는 게임 상태입니다.");
    }

    @Override
    public ChessGameState end() {
        throw new UnsupportedOperationException("게임이 끝났거나, 시작되지 않은 상태입니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public Color getTurn() {
        throw new UnsupportedOperationException("존재하는 턴이 없습니다.");
    }
}
