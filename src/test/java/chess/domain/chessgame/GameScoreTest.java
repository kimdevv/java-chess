package chess.domain.chessgame;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameScoreTest {

    @DisplayName("각 진영의 게임 점수를 올바르게 계산한다")
    @Test
    void testCalculateTotalGameScore() {
        ChessBoard chessBoard = ChessBoard.initialBoard();
        Camp camp = Camp.BLACK;
        GameScore gameScore = new GameScore(chessBoard, camp);
        assertThat(gameScore.getTotalScore()).isEqualTo(38);
    }

    @DisplayName("폰이 같은 세로줄에 있는 경우 점수를 올바르게 계산한다")
    @Test
    void testCalculateTotalGameScore_WhenPawnIsSameVerticalLine() {
        ChessBoard chessBoard = ChessBoard.initialBoard();
        chessBoard.movePiece(new Square(Lettering.B, Numbering.SEVEN), new Square(Lettering.A, Numbering.SIX));
        Camp camp = Camp.BLACK;
        GameScore gameScore = new GameScore(chessBoard, camp);
        assertThat(gameScore.getTotalScore()).isEqualTo(37);
    }
}
