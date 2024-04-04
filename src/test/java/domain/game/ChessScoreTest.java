package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import domain.piece.BlackPawn;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessScoreTest {

    ChessScore chessScore = new ChessScore();

    @DisplayName("체스판의 초기 상태에서의 점수는 38점이다.")
    @Test
    void calculateScoreInitialBoard() {
        ChessBoard chessBoard = new ChessBoard();
        Map<Coordinate, ChessPiece> board = chessBoard.getBoard();

        double expectedScore = 38.0d;
        double actualWhiteScore = chessScore.getScore(board, Color.WHITE);
        double actualBlackScore = chessScore.getScore(board, Color.BLACK);

        assertThat(actualWhiteScore).isEqualTo(expectedScore);
        assertThat(actualBlackScore).isEqualTo(expectedScore);
    }

    @DisplayName("같은 색상의 폰이 같은 세로줄에 있으면 0.5점으로 계산한다.")
    @Test
    void calculatePawnScore() {
        /*
         Pp......
         Pp......
         ........
         ........
         ........
         ........
         ........
         ........
         */
        Map<Coordinate, ChessPiece> board = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            board.put(new Coordinate(i, 0), new BlackPawn());
            board.put(new Coordinate(i, 1), new WhitePawn());
        }
        for (int i = 2; i < 8; i++) {
            board.put(new Coordinate(0, i), Blank.getInstance());
            board.put(new Coordinate(1, i), Blank.getInstance());
        }

        double expectedScore = 1.0d;
        double actualWhiteScore = chessScore.getScore(board, Color.WHITE);
        double actualBlackScore = chessScore.getScore(board, Color.BLACK);

        assertThat(actualWhiteScore).isEqualTo(expectedScore);
        assertThat(actualBlackScore).isEqualTo(expectedScore);
    }
}
