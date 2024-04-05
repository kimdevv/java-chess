package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;
import score.Score;

public abstract class ChessPieceBase implements ChessPiece {

    private final Color color;
    private final Score score;

    public ChessPieceBase(Color color, Score score) {
        this.color = color;
        this.score = score;
    }

    @Override
    public abstract Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack);

    @Override
    public abstract boolean isPawn();

    @Override
    public abstract boolean isKing();

    @Override
    public abstract boolean isBlank();

    @Override
    public boolean isOpponentColor(Color color) {
        return this.color != color;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getScore() {
        return score.getScore();
    }
}
