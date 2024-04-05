package chess.service;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Side;
import chess.domain.piece.WhitePawn;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BoardConverter {

    BLACK_ROOK(() -> new Rook(Side.BLACK), "R"),
    WHITE_ROOK(() -> new Rook(Side.WHITE), "r"),
    BLACK_KNIGHT(() -> new Knight(Side.BLACK), "N"),
    WHITE_KNIGHT(() -> new Knight(Side.WHITE), "n"),
    BLACK_BISHOP(() -> new Bishop(Side.BLACK), "B"),
    WHITE_BISHOP(() -> new Bishop(Side.WHITE), "b"),
    BLACK_QUEEN(() -> new Queen(Side.BLACK), "Q"),
    WHITE_QUEEN(() -> new Queen(Side.WHITE), "q"),
    BLACK_KING(() -> new King(Side.BLACK), "K"),
    WHITE_KING(() -> new King(Side.WHITE), "k"),
    BLACK_PAWN(BlackPawn::new, "P"),
    WHITE_PAWN(WhitePawn::new, "p"),
    EMPTY(Empty::instance, "."),
    ;

    private final Supplier<Piece> toPiece;
    private final String string;

    BoardConverter(Supplier<Piece> toPiece, String string) {
        this.toPiece = toPiece;
        this.string = string;
    }

    private Piece piece() {
        return toPiece.get();
    }

    private static String toString(Piece piece) {
        return Arrays.stream(values())
                .filter(it -> it.piece().equals(piece))
                .findAny()
                .map(it -> it.string)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Piece"));
    }

    private static Piece toPiece(String string) {
        return Arrays.stream(values())
                .filter(it -> it.string.equals(string))
                .findAny()
                .map(BoardConverter::piece)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 String"));
    }

    public static String convertToString(List<Piece> pieces) {
        return pieces.stream()
                .map(BoardConverter::toString)
                .collect(Collectors.joining(""));
    }

    public static Map<Position, Piece> convertToBoard(String string) {
        List<Piece> pieces = convertToPieces(string);
        List<Position> positions = Position.allPositions();

        validateSameSize(pieces, positions);

        return IntStream.range(0, positions.size())
                .boxed()
                .collect(Collectors.toMap(
                        positions::get,
                        pieces::get,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private static List<Piece> convertToPieces(String string) {
        String[] split = string.split("");

        return Arrays.stream(split)
                .map(BoardConverter::toPiece)
                .toList();
    }

    private static void validateSameSize(List<Piece> pieces, List<Position> positions) {
        if (pieces.size() != positions.size()) {
            throw new IllegalArgumentException("유효하지 않은 Pieces");
        }
    }
}
