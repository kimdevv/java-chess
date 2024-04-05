package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.coordinate.position.Column;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardScoreCalculator {

    public static final double SAME_COLUMN_PAWN_SCORE = 0.5;

    public static double calculate(Color color, Map<Coordinate, ChessPiece> board) {
        return calculateChessBoardScore(color, board);
    }

    private static double calculateChessBoardScore(Color color, Map<Coordinate, ChessPiece> board) {
        List<ChessPiece> coloredPiece = findSameColorPiece(color, board);

        return calculateTotalScoreWithOutPawn(coloredPiece) + calculatePawnScore(coloredPiece, color, board);
    }

    private static double calculateTotalScoreWithOutPawn(List<ChessPiece> coloredPiece) {
        return coloredPiece.stream()
                .filter(piece -> !piece.isPawn())
                .map(ChessPiece::getScore)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private static double calculatePawnScore(List<ChessPiece> coloredPiece, Color color,
                                             Map<Coordinate, ChessPiece> board) {
        double pawnScore = coloredPiece.stream()
                .filter(ChessPiece::isPawn)
                .map(ChessPiece::getScore)
                .mapToDouble(Double::doubleValue)
                .sum();

        return pawnScore - sameColumnPawnScore(color, board);
    }


    private static double sameColumnPawnScore(Color color, Map<Coordinate, ChessPiece> board) {
        return SAME_COLUMN_PAWN_SCORE * countSameColumPawn(color, board);
    }

    private static List<ChessPiece> findSameColorPiece(Color color, Map<Coordinate, ChessPiece> board) {
        return board.values()
                .stream()
                .filter(chessPiece -> chessPiece.isSameColor(color))
                .toList();
    }

    private static double countSameColumPawn(Color color, Map<Coordinate, ChessPiece> board) {
        Map<Column, Long> eachColumnPawns = findEachColumnPawn(color, board);

        return eachColumnPawns.values()
                .stream()
                .filter(pawnCount -> pawnCount > 1)
                .mapToDouble(Long::doubleValue)
                .sum();
    }

    private static Map<Column, Long> findEachColumnPawn(Color color, Map<Coordinate, ChessPiece> board) {
        return board.keySet().stream()
                .filter(coordinate -> isSameColorPawn(coordinate, color, board))
                .collect(Collectors.groupingBy(
                        Coordinate::getColumn, Collectors.counting()
                ));
    }

    private static boolean isSameColorPawn(Coordinate coordinate, Color color, Map<Coordinate, ChessPiece> board) {
        ChessPiece chessPiece = board.get(coordinate);

        if (chessPiece.isPawn()) {
            return chessPiece.isSameColor(color);
        }
        return false;
    }
}
