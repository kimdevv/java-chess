package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final double VERTICAL_PAWN_SCORE = 0.5;
    private static final int VERTICAL_PAWN_COUNT = 2;

    public ChessGameResult calculate(Map<Position, Piece> pieces) {
        Map<Position, Piece> whitePieces = collectPiecesByColor(Color.WHITE, pieces);
        Map<Position, Piece> blackPieces = collectPiecesByColor(Color.BLACK, pieces);

        return createChessGameResult(whitePieces, blackPieces);
    }

    private Map<Position, Piece> collectPiecesByColor(Color color, Map<Position, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private ChessGameResult createChessGameResult(Map<Position, Piece> whitePieces, Map<Position, Piece> blackPieces) {
        double whiteScore = calculateScoreByColor(whitePieces);
        double blackScore = calculateScoreByColor(blackPieces);

        return ChessGameResult.of(whiteScore, blackScore);
    }

    private double calculateScoreByColor(Map<Position, Piece> pieces) {
        double defaultScoreSum = sumDefaultPieceScore(pieces);
        long pawnCount = getPawnCount(pieces);

        return defaultScoreSum - pawnCount * VERTICAL_PAWN_SCORE;
    }

    private double sumDefaultPieceScore(Map<Position, Piece> pieces) {
        return pieces.values().stream()
                .mapToDouble(Piece::getScore)
                .reduce(0, Double::sum);
    }

    private long getPawnCount(Map<Position, Piece> pieces) {
        return getPawnCountGroupingBySameFile(pieces).values().stream()
                .filter(count -> count >= VERTICAL_PAWN_COUNT)
                .mapToLong(Long::longValue)
                .sum();
    }

    private static Map<Integer, Long> getPawnCountGroupingBySameFile(Map<Position, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().getFile(), Collectors.counting()));
    }
}
