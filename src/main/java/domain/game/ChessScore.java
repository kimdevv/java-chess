package domain.game;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.piece.type.PieceType;
import java.util.List;
import java.util.Map;

public class ChessScore {

    public double getScore(Map<Coordinate, ChessPiece> board, Color color) {
        return calculateScoreExceptPawn(board, color) + calculatePawnScore(board, color);
    }

    private double calculateScoreExceptPawn(Map<Coordinate, ChessPiece> board, Color color) {
        return board.values().stream()
                .filter(piece -> !piece.isSameType(PieceType.PAWN) && piece.hasSameColor(color))
                .mapToDouble(ChessPiece::getDefaultScore)
                .sum();
    }

    private double calculatePawnScore(Map<Coordinate, ChessPiece> board, Color color) {
        double sum = 0D;
        for (int column = 0; column < 8; column++) {
            sum += calculatePawnScoreInColumn(board, color, column);
        }
        return sum;
    }

    /**
     * @param board 체스 판
     * @param color 점수를 계산하고자 하는 말의 색상
     * @param column 열의 번호
     * @return 해당 열에 있는 폰을 구하고, 폰의 개수에 따라 기본 점수 혹은 0.5로 계산한다.
     */
    private double calculatePawnScoreInColumn(Map<Coordinate, ChessPiece> board, Color color, int column) {
        List<ChessPiece> pawnInColumn = getPawnInColumn(board, color, column);
        double sum = pawnInColumn.stream()
                .mapToDouble(ChessPiece::getDefaultScore)
                .sum();

        if (pawnInColumn.size() > 1) {
            return sum / 2;
        }
        return sum;
    }

    private List<ChessPiece> getPawnInColumn(Map<Coordinate, ChessPiece> board, Color color, int column) {
        return board.keySet().stream()
                .filter(coordinate -> coordinate.isSameColumn(column))
                .map(board::get)
                .filter(piece -> piece.isSameType(PieceType.PAWN) && piece.hasSameColor(color))
                .toList();
    }
}
