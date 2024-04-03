package chess.domain.piece;

import java.util.function.Function;

public enum Type {
    KING(0.0, King::new),
    QUEEN(9.0, Queen::new),
    ROOK(5.0, Rook::new),
    BISHOP(3.0, Bishop::new),
    KNIGHT(2.5, Knight::new),
    PAWN(1, Pawn::new),
    EMPTY(0.0, team -> Empty.INSTANCE);

    private final double score;
    private final Function<Team, Piece> function;

    Type(final double score, final Function<Team, Piece> function) {
        this.score = score;
        this.function = function;
    }

    public Piece getPiece(final Team team) {
        return function.apply(team);
    }

    public double getScore() {
        return score;
    }
}
