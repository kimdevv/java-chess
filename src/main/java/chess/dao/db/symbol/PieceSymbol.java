package chess.dao.db.symbol;

import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceSymbol {
    KING(PieceType.KING, "king"),
    QUEEN(PieceType.QUEEN, "queen"),
    KNIGHT(PieceType.KNIGHT, "knight"),
    BISHOP(PieceType.BISHOP, "bishop"),
    ROOK(PieceType.ROOK, "rook"),
    WHITE_PAWN(PieceType.WHITE_PAWN, "white_pawn"),
    BLACK_PAWN(PieceType.BLACK_PAWN, "black_pawn"),
    ;

    private final PieceType pieceType;
    private final String symbol;

    PieceSymbol(final PieceType pieceType, final String symbol) {
        this.pieceType = pieceType;
        this.symbol = symbol;
    }

    public static PieceSymbol getPieceSymbolByPieceType(PieceType pieceType) {
        return Arrays.stream(PieceSymbol.values()).filter(pieceSymbol -> pieceSymbol.pieceType == pieceType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("저장할 수 없는 체스 말 타입입니다."));
    }

    public static PieceSymbol getPieceSymbolBySymbol(String symbol) {
        return Arrays.stream(PieceSymbol.values()).filter(pieceSymbol -> pieceSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불러 올 수 없는 체스 말 타입 입니다."));
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public String getSymbol() {
        return symbol;
    }
}
