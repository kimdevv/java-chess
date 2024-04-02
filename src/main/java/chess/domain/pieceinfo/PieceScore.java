package chess.domain.pieceinfo;

public enum PieceScore {
    QUEEN_SCORE(9),
    ROOK_SCORE(5),
    BISHOP_SCORE(3),
    KNIGHT_SCORE(2.5),
    PAWN_SCORE(1),
    PAWN_LOWER_SCORE(0.5);

    private final double score;

    PieceScore(double score) {
        this.score = score;
    }

    public double get() {
        return score;
    }
}
