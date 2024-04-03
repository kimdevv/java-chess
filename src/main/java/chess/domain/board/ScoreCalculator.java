package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final double PAWN_PENALTY_SCORE = 0.5;
    private static final int PAWNS_IN_FILE_THRESHOLD = 2;

    private ScoreCalculator() {
    }

    public static double calculateScore(Map<Position, Piece> colorPieces) {
        double defaultScore = calculateDefaultScore(colorPieces);
        double pawnPenaltyScore = calculatePawnPenaltyScore(colorPieces);

        return defaultScore - pawnPenaltyScore;
    }

    private static double calculateDefaultScore(Map<Position, Piece> colorPieces) {
        return colorPieces.values().stream()
                .mapToDouble(Piece::score)
                .sum();
    }

    private static double calculatePawnPenaltyScore(Map<Position, Piece> colorPieces) {
        Map<Integer, Long> pawnFilesCount = countPawnsInEachFile(colorPieces);

        return pawnFilesCount.values().stream()
                .filter(count -> count >= PAWNS_IN_FILE_THRESHOLD)
                .mapToDouble(count -> PAWN_PENALTY_SCORE * count)
                .sum();
    }

    private static Map<Integer, Long> countPawnsInEachFile(Map<Position, Piece> colorPieces) {
        return colorPieces.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .map(entry -> entry.getKey().getFile())
                .collect(Collectors.groupingBy(file -> file, Collectors.counting()));
    }
}
