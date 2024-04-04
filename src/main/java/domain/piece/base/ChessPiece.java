package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;
import domain.piece.type.PieceType;

public interface ChessPiece {

    Direction getDirection(Coordinate start, Coordinate destination);

    double getDefaultScore();

    boolean hasSameColor(Color color);

    boolean isSameType(PieceType pieceType);

    PieceType getType();

    Color getColor();
}
