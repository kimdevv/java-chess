package chess.domain.chesspiece;

public enum Team {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }
}
