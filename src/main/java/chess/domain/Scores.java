package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.util.Calculator;
import java.util.Map;

public class Scores { //TODO 도메인인가 DTO인가

    private final double blackScore;
    private final double whiteScore;

    private Scores(final double blackScore, final double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static Scores from(final ChessBoard chessBoard) {
        final Map<Position, Piece> blackPieces = chessBoard.getPiecesWithPositionBy(Color.WHITE);
        final Map<Position, Piece> whitePieces = chessBoard.getPiecesWithPositionBy(Color.BLACK);

        return new Scores(Calculator.calculateScore(blackPieces), Calculator.calculateScore(whitePieces));
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
