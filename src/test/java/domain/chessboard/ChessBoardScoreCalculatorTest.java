package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardScoreCalculatorTest {


    @DisplayName("현재 체스판의 블랙 말 점수를 계산할 수 있다.")
    @Test
    void calculateBlackPieceScore() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.createInitialBoard());

        Assertions.assertThat(ChessBoardScoreCalculator.calculate(Color.BLACK, chessBoard.getBoard())).isEqualTo(38.0);
    }

    @DisplayName("현재 체스판의 하얀 말의 점수를 계산할 수 있다.")
    @Test
    void calculateWhitePieceScore() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.createInitialBoard());

        Assertions.assertThat(ChessBoardScoreCalculator.calculate(Color.WHITE, chessBoard.getBoard())).isEqualTo(38.0);
    }

    @DisplayName("현재 체스판에 같은 색의 폰이 같은 세로줄에 존재할 경우 각 0.5로 계산한다.")
    @Test
    void calculateWhenSameColumnPawnExist() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.createInitialBoard());

        chessBoard.playTurn(Coordinate.from("a2"), Coordinate.from("a4"));
        chessBoard.playTurn(Coordinate.from("A7"), Coordinate.from("A5"));
        chessBoard.playTurn(Coordinate.from("b2"), Coordinate.from("b4"));
        chessBoard.playTurn(Coordinate.from("C7"), Coordinate.from("C6"));
        chessBoard.playTurn(Coordinate.from("b4"), Coordinate.from("a5"));

        Assertions.assertThat(ChessBoardScoreCalculator.calculate(Color.WHITE, chessBoard.getBoard())).isEqualTo(37.0);
    }
}
