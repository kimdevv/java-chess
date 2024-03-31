package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.color.Color;
import chess.domain.position.Positions;

public final class BlankChessState extends ChessState {

    public BlankChessState(Board board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Positions positions) {
        throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
    }
}
