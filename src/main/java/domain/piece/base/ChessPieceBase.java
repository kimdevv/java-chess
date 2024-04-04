package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;
import domain.piece.type.PieceType;

public abstract class ChessPieceBase implements ChessPiece {

    private final PieceType pieceType;
    private final Color color;

    public ChessPieceBase(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    @Override
    public abstract double getDefaultScore();

    @Override
    public abstract Direction getDirection(Coordinate start, Coordinate destination);

    @Override
    public boolean hasSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    @Override
    public PieceType getType() {
        return pieceType;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
