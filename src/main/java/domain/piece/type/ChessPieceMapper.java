package domain.piece.type;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.Arrays;

public enum ChessPieceMapper {

    WHITE_PAWN(PieceType.PAWN, Color.WHITE, new WhitePawn()),
    BLACK_PAWN(PieceType.PAWN, Color.BLACK, new BlackPawn()),

    WHITE_KING(PieceType.KING, Color.WHITE, new King(Color.WHITE)),
    BLACK_KING(PieceType.KING, Color.BLACK, new King(Color.BLACK)),

    WHITE_BISHOP(PieceType.BISHOP, Color.WHITE, new Bishop(Color.WHITE)),
    BLACK_BISHOP(PieceType.BISHOP, Color.BLACK, new Bishop(Color.BLACK)),

    WHITE_QUEEN(PieceType.QUEEN, Color.WHITE, new Queen(Color.WHITE)),
    BLACK_QUEEN(PieceType.QUEEN, Color.BLACK, new Queen(Color.BLACK)),

    WHITE_KNIGHT(PieceType.KNIGHT, Color.WHITE, new Knight(Color.WHITE)),
    BLACK_KNIGHT(PieceType.KNIGHT, Color.BLACK, new Knight(Color.BLACK)),

    WHITE_ROOK(PieceType.ROOK, Color.WHITE, new Rook(Color.WHITE)),
    BLACK_ROOK(PieceType.ROOK, Color.BLACK, new Rook(Color.BLACK)),

    BLANK(PieceType.BLANK, Color.ANY, Blank.getInstance());

    private final PieceType pieceType;
    private final Color color;
    private final ChessPiece instance;

    ChessPieceMapper(PieceType pieceType, Color color, ChessPiece instance) {
        this.pieceType = pieceType;
        this.color = color;
        this.instance = instance;
    }

    public static ChessPiece getInstance(PieceType pieceType, Color color) {
        return Arrays.stream(values())
                .filter(piece -> piece.isSamePiece(pieceType, color))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당되는 말이 존재하지 않습니다."))
                .instance;
    }

    private boolean isSamePiece(PieceType pieceType, Color color) {
        return this.pieceType == pieceType && this.color == color;
    }
}
