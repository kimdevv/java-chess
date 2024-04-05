package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public interface ChessPiece {

    boolean isOpponentColor(Color color);

    boolean isSameColor(Color color);

    boolean isKing();

    boolean isPawn();

    boolean isBlank();

    double getScore();

    Color getColor();

    Direction getDirection(Coordinate coordinate, Coordinate destination, boolean canAttack);
}
