package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFactory {

    private static final Rank WHITE_PIECE_START_RANK = Rank.ONE;
    private static final Rank WHITE_PAWN_START_RANK = Rank.TWO;
    private static final Rank BLACK_PIECE_START_RANK = Rank.EIGHT;
    private static final Rank BLACK_PAWN_START_RANK = Rank.SEVEN;

    public static Board create() {
        Map<Position, Piece> initialPiecePositions = generateEmptyBoard();
        initialPiecePositions.putAll(getWhitePieces());
        initialPiecePositions.putAll(getBlackPieces());
        return new Board(initialPiecePositions);
    }

    private static Map<Position, Piece> generateEmptyBoard() {
        return Arrays.stream(Rank.values())
                .flatMap(BoardFactory::generateHorizontalLine)
                .collect(generateEntry());
    }

    private static Stream<Position> generateHorizontalLine(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank));
    }

    private static Collector<Position, ?, Map<Position, Piece>> generateEntry() {
        return Collectors.toMap(position -> position, position -> Piece.EMPTY_PIECE);
    }

    private static Map<Position, Piece> getWhitePieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.WHITE, WHITE_PIECE_START_RANK));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.WHITE, WHITE_PAWN_START_RANK));
        return initialWhitePiecePositions;
    }

    private static Map<Position, Piece> getBlackPieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.BLACK, BLACK_PIECE_START_RANK));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.BLACK, BLACK_PAWN_START_RANK));
        return initialWhitePiecePositions;
    }

    private static Map<Position, Piece> getNotPawnsPieces(final Color color, final Rank rank) {
        return Map.of(Position.of(File.A, rank), Piece.of(PieceType.ROOK, color),
                Position.of(File.B, rank), Piece.of(PieceType.KNIGHT, color),
                Position.of(File.C, rank), Piece.of(PieceType.BISHOP, color),
                Position.of(File.D, rank), Piece.of(PieceType.QUEEN, color),
                Position.of(File.E, rank), Piece.of(PieceType.KING, color),
                Position.of(File.F, rank), Piece.of(PieceType.BISHOP, color),
                Position.of(File.G, rank), Piece.of(PieceType.KNIGHT, color),
                Position.of(File.H, rank), Piece.of(PieceType.ROOK, color));
    }

    private static Map<Position, Piece> getPawnsPieces(final Color color, final Rank rank) {
        return Arrays.stream(File.values())
                .collect(Collectors.toMap(file -> Position.of(file, rank), file -> Pawn.of(color)));
    }
}
