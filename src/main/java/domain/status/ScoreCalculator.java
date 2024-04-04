package domain.status;

import domain.Side;
import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScoreCalculator {

    ChessBoard chessBoard;

    public ScoreCalculator(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public double sumPiecesScore(Side side) {
        return sumMajorPieceScore(chessBoard.collectMajorPieces(side)) +
                sumPawnScore(chessBoard.collectPawns(side));
    }

    private double sumMajorPieceScore(List<Piece> majorPieces) {
        return majorPieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double sumPawnScore(Map<Position, Piece> pawns) {
        return pawns.entrySet().stream()
                .mapToDouble(entry -> pawnScore(entry.getKey(), entry.getValue(), pawns.keySet()))
                .sum();
    }

    private double pawnScore(Position pawnPosition, Piece pawn, Set<Position> positions) {
        return positions.stream()
                .filter(position -> !pawnPosition.equals(position))
                .filter(pawnPosition::isSameFile)
                .findFirst()
                .map(score -> pawn.decreaseScore())
                .orElse(pawn.getScore());
    }
}
