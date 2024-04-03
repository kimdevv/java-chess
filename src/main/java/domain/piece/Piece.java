package domain.piece;

import domain.position.Position;

public interface Piece {
    void validateMovement(Position source, Position target, Color targetColor);

    Color color();

    Type type();

    default double score() {
        return type().score();
    }

    default boolean isSameColor(Color color) {
        return color().isSameColor(color);
    }

    default boolean isNotOppositeColor(Color color) {
        return color().isNotOppositeColor(color);
    }

    default boolean isSameType(Type type) {
        return type().isSameType(type);
    }
}
