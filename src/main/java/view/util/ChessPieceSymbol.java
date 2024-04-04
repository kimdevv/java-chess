package view.util;

import domain.piece.type.PieceType;
import java.util.Arrays;

public enum ChessPieceSymbol {

    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    ROOK(PieceType.ROOK, "r"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    PAWN(PieceType.PAWN, "p"),
    NONE(PieceType.BLANK, ".");

    private final PieceType pieceType;
    private final String symbol;

    ChessPieceSymbol(PieceType pieceType, String symbol) {
        this.pieceType = pieceType;
        this.symbol = symbol;
    }

    public static String getSymbol(PieceType pieceType) {
        return Arrays.stream(values())
                .filter(piece -> piece.pieceType == pieceType)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다."))
                .symbol;
    }
}
