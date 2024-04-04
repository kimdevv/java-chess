package dao.mapper;

import domain.Side;
import domain.piece.*;

import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceDaoMapper {

    BLACK_ROOK("R", () -> new Rook(Side.BLACK)),
    BLACK_KNIGHT("N", () -> new Knight(Side.BLACK)),
    BLACK_BISHOP("B", () -> new Bishop(Side.BLACK)),
    BLACK_QUEEN("Q", () -> new Queen(Side.BLACK)),
    BLACK_KING("K", () -> new King(Side.BLACK)),
    BLACK_PAWN("P", () -> new Pawn(Side.BLACK)),

    WHITE_ROOK("r", () -> new Rook(Side.WHITE)),
    WHITE_KNIGHT("n", () -> new Knight(Side.WHITE)),
    WHITE_BISHOP("b", () -> new Bishop(Side.WHITE)),
    WHITE_QUEEN("q", () -> new Queen(Side.WHITE)),
    WHITE_KING("k", () -> new King(Side.WHITE)),
    WHITE_PAWN("p", () -> new Pawn(Side.WHITE)),
    ;

    private final String dbData;
    private final Supplier<Piece> supplier;

    PieceDaoMapper(String dbData, Supplier<Piece> supplier) {
        this.dbData = dbData;
        this.supplier = supplier;
    }

    public static Piece dataToPiece(String data) {
        return Arrays.stream(values())
                .filter(piece -> data.equals(piece.dbData))
                .findFirst()
                .map(piece -> piece.supplier.get())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 data가 없습니다."));
    }

    public static String pieceToData(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceDaoMapper -> piece.equals(pieceDaoMapper.supplier.get()))
                .findFirst()
                .map(pieceDaoMapper -> pieceDaoMapper.dbData)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 piece가 없습니다."));
    }
}
