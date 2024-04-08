package chess.domain.game.status;

import chess.domain.board.Board;

public abstract class Running implements GameStatus {
    private final Board board;

    protected Running(final Board board) {
        this.board = board;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    protected Board board() {
        return board;
    }
}
