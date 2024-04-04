package view.mapper;

import domain.piece.*;

import java.util.Arrays;

public enum PieceMapper {

    ROOK(Rook.class, "R"),
    KNIGHT(Knight.class, "N"),
    BISHOP(Bishop.class, "B"),
    QUEEN(Queen.class, "Q"),
    KING(King.class, "K"),
    PAWN(Pawn.class, "P"),
    ;

    private final Class<? extends Piece> pieceType;
    private final String symbol;

    PieceMapper(Class<? extends Piece> pieceType, String symbol) {
        this.pieceType = pieceType;
        this.symbol = symbol;
    }

    public static String toSymbol(Piece piece) {
        String symbol = Arrays.stream(values())
                .filter(value -> value.pieceType == piece.getClass())
                .findFirst()
                .map(value -> value.symbol)
                .orElseThrow();

        if (piece.isBlack()) {
            return symbol;
        }
        return symbol.toLowerCase();
    }
}
