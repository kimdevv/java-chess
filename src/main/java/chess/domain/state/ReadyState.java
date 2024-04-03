package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Position;

public class ReadyState implements GameState {
    private static final Color FIRST_TURN = Color.WHITE;

    @Override
    public GameState start() {
        return new MoveState(FIRST_TURN);
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        throw new UnsupportedOperationException("준비 상태에서는 움직일 수 없습니다.");
    }

    @Override
    public GameState end() {
        return new EndState();
    }

    @Override
    public GameState status() {
        throw new UnsupportedOperationException("준비 상태에서는 상태를 볼 수 없습니다.");
    }

    @Override
    public boolean isPlaying() {
        return true;
    }

    @Override
    public Color getCurrentColor() {
        return FIRST_TURN;
    }
}
