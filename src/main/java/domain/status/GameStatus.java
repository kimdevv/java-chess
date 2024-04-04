package domain.status;

import domain.Side;
import domain.board.ChessBoard;

public class GameStatus {

    private final double blackScore;
    private final double whiteScore;

    private GameStatus(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static GameStatus from(ChessBoard chessBoard) {
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard);

        double blackScore = scoreCalculator.sumPiecesScore(Side.BLACK);
        double whiteScore = scoreCalculator.sumPiecesScore(Side.WHITE);
        return new GameStatus(blackScore, whiteScore);
    }

    public double blackScore() {
        return blackScore;
    }

    public double whiteScore() {
        return whiteScore;
    }

    public Side winner() {
        if (blackScore > whiteScore) {
            return Side.BLACK;
        }
        if (blackScore == whiteScore) {
            return Side.NONE;
        }
        return Side.WHITE;
    }
}
