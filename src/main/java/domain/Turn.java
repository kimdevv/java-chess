package domain;

import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Objects;

public class Turn {

    private Side side;

    public Turn() {
        this(Side.WHITE);
    }

    public Turn(Side side) {
        this.side = side;
    }

    public void check(ChessBoard chessBoard, Position current) {
        Piece currentPiece = chessBoard.findPiece(current);

        if (currentPiece.isNotSameSide(side)) {
            throw new IllegalArgumentException("해당 진영의 턴이 아닙니다.");
        }
    }

    public void end() {
        side = side.opponent();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Turn turn = (Turn) object;
        return side == turn.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
