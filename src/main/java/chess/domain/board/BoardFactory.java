package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.point.Point;
import chess.dto.PieceDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactory {
    private static final int MINIMUM_RANK = 1;
    private static final int MAXIMUM_RANK = 8;
    private static final char MINIMUM_FILE = 'a';
    private static final char MAXIMUM_FILE = 'h';

    public static Map<Point, Piece> createEmptyBoard() {
        final Map<Point, Piece> board = new HashMap<>();

        IntStream.rangeClosed(MINIMUM_RANK, MAXIMUM_RANK)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);

        return board;
    }

    public static Map<Point, Piece> createInitialChessBoard() {
        final Map<Point, Piece> board = new HashMap<>();

        board.putAll(createEighthLine());
        board.putAll(createSeventhLine());
        IntStream.rangeClosed(3, 6)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);
        board.putAll(createSecondLine());
        board.putAll(createFirstLine());
        return board;
    }

    private static Map<Point, Piece> createEighthLine() {
        return lineOfKing(8, Team.BLACK);
    }

    private static Map<Point, Piece> createSeventhLine() {
        return lineOfPawn(7, Team.BLACK);
    }

    private static Map<Point, Piece> createSecondLine() {
        return lineOfPawn(2, Team.WHITE);
    }

    private static Map<Point, Piece> createFirstLine() {
        return lineOfKing(1, Team.WHITE);
    }

    private static Map<Point, Piece> lineOfPawn(final int rank, final Team team) {
        final Map<Point, Piece> line = new HashMap<>();

        for (char file = MINIMUM_FILE; file <= MAXIMUM_FILE; file++) {
            line.put(Point.of(file, rank), new Pawn(team));
        }
        return line;
    }

    private static Map<Point, Piece> lineOfKing(final int rank, final Team team) {
        return Map.of(
                Point.of('a', rank), new Rook(team),
                Point.of('b', rank), new Knight(team),
                Point.of('c', rank), new Bishop(team),
                Point.of('d', rank), new Queen(team),
                Point.of('e', rank), new King(team),
                Point.of('f', rank), new Bishop(team),
                Point.of('g', rank), new Knight(team),
                Point.of('h', rank), new Rook(team)
        );
    }

    private static Map<Point, Piece> lineOfEmpty(final int rank) {
        final Map<Point, Piece> line = new HashMap<>();

        for (char file = MINIMUM_FILE; file <= MAXIMUM_FILE; file++) {
            line.put(Point.of(file, rank), Empty.INSTANCE);
        }
        return line;
    }

    public static Map<Point, Piece> loadPreviousChessBoard(final List<PieceDto> pieceDtos) {
        return pieceDtos.stream()
                .collect(Collectors.toMap(PieceDto::getPoint, PieceDto::getPiece));
    }
}
