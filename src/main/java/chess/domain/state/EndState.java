package chess.domain.state;

import chess.domain.Board;
import chess.domain.position.Position;

public class EndState implements GameState {
    private static final EndState INSTANCE = new EndState();

    private EndState() {
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("종료 상태에서는 시작할 수 없습니다.");
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        throw new UnsupportedOperationException("종료 상태에서는 움직일 수 없습니다.");
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException("종료 상태에서는 종료할 수 없습니다.");
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException("종료 상태에서는 점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    public static EndState getInstance() {
        return EndState.INSTANCE;
    }
}
