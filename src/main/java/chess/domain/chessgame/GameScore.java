package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GameScore {

    private static final double REDUCED_SCORE_FOR_PAWN = 0.5;

    private final Camp camp;
    private final double totalScore;

    public GameScore(ChessBoard chessBoard, Camp camp) {
        this.camp = camp;
        this.totalScore = calculateTotalScore(chessBoard, camp);
    }

    private double calculateTotalScore(ChessBoard chessBoard, Camp camp) {
        List<ChessPieceType> chessPieceTypes = chessPieceTypesByCamp(chessBoard, camp);
        double totalScore = chessPieceTypes.stream()
                .mapToDouble(ChessPieceType::findChessPieceScore)
                .sum();
        return totalScore - REDUCED_SCORE_FOR_PAWN * pawnCountWhereSameVerticalLine(chessBoard, camp);
    }

    private List<ChessPieceType> chessPieceTypesByCamp(ChessBoard chessBoard, Camp camp) {
        return filterChessPiecesByCamp(chessBoard, camp)
                .map(ChessPiece::getChessPieceType)
                .toList();
    }

    private long pawnCountWhereSameVerticalLine(ChessBoard chessBoard, Camp camp) {
        List<Lettering> letterings = extractPawnLetterings(chessBoard, camp);
        return countDuplicates(letterings);
    }

    private Stream<ChessPiece> filterChessPiecesByCamp(ChessBoard chessBoard, Camp camp) {
        return chessBoard.getBoard()
                .values()
                .stream()
                .filter(board -> board.getCamp().isSameCamp(camp));
    }

    private List<Lettering> extractPawnLetterings(ChessBoard chessBoard, Camp camp) {
        return chessBoard.getBoard()
                .entrySet()
                .stream()
                .filter(entry -> isSameCampPawn(entry.getValue(), camp))
                .map(Map.Entry::getKey)
                .map(Square::getLettering)
                .toList();
    }

    private boolean isSameCampPawn(ChessPiece chessPiece, Camp camp) {
        return chessPiece.getCamp().isSameCamp(camp) && chessPiece.isChessPiecePawn();
    }

    private long countDuplicates(List<Lettering> letterings) {
        return letterings.stream()
                .filter(lettering -> Collections.frequency(letterings, lettering) > 1)
                .count();
    }

    public Camp winCamp(GameScore gameScore) {
        if (isTotalScoreHigh(gameScore)) {
            return camp;
        }
        if (isTotalScoreLow(gameScore)) {
            return camp.findEnemyCamp();
        }
        return Camp.NONE;
    }

    private boolean isTotalScoreHigh(GameScore gameScore) {
        return totalScore > gameScore.totalScore;
    }

    private boolean isTotalScoreLow(GameScore gameScore) {
        return totalScore < gameScore.totalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }
}
