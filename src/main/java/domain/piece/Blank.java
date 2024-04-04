package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPiece;
import domain.piece.type.PieceType;

public class Blank implements ChessPiece {

    private static final double NONE = 0d;

    private static final Blank instance = new Blank();

    private Blank() {
    }

    public static Blank getInstance() {
        return instance;
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        throw new IllegalStateException("이동할 말이 존재하지 않습니다.");
    }

    @Override
    public double getDefaultScore() {
        return NONE;
    }

    @Override
    public boolean hasSameColor(Color color) {
        return true;
    }

    @Override
    public boolean isSameType(PieceType pieceType) {
        return PieceType.BLANK == pieceType;
    }

    @Override
    public PieceType getType() {
        return PieceType.BLANK;
    }

    @Override
    public Color getColor() {
        return Color.ANY;
    }
}
