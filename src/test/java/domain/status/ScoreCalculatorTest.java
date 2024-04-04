package domain.status;

import domain.Side;
import domain.board.ChessBoard;
import domain.board.ChessBoardInitializer;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PieceFixture;
import fixture.PositionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ScoreCalculatorTest {

    @DisplayName("기물들의 점수를 계산한다.")
    @Test
    void calculateMajorPieceScore() {

        ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.init());
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard);

        Assertions.assertThat(scoreCalculator.sumPiecesScore(Side.BLACK)).isEqualTo(38);
    }

    @DisplayName("같은 세로줄에 폰이 없으면 각 폰은 1점으로 점수를 계산한다.")
    @Test
    void calculateNoSameFilePawnScore() {

        Map<Position, Piece> onlyPawnsBoard = new HashMap<>();
        onlyPawnsBoard.put(PositionFixture.a2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.b2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.c2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.d2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.e2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.f2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.g2(), PieceFixture.blackPawn());
        onlyPawnsBoard.put(PositionFixture.h2(), PieceFixture.blackPawn());

        ChessBoard chessBoard = new ChessBoard(onlyPawnsBoard);
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard);

        Assertions.assertThat(scoreCalculator.sumPiecesScore(Side.BLACK)).isEqualTo(8);
    }

    @DisplayName("같은 세로줄에 폰이 있으면 그 폰들은 0.5점으로 점수를 계산한다.")
    @Test
    void calculateSameFilePawnScore() {

        Map<Position, Piece> board = new HashMap<>();
        board.put(PositionFixture.a2(), PieceFixture.blackPawn());
        board.put(PositionFixture.a3(), PieceFixture.blackPawn());
        board.put(PositionFixture.a4(), PieceFixture.blackPawn());
        board.put(PositionFixture.a5(), PieceFixture.blackPawn());
        board.put(PositionFixture.b6(), PieceFixture.blackPawn());
        board.put(PositionFixture.b7(), PieceFixture.blackPawn());
        board.put(PositionFixture.c2(), PieceFixture.blackPawn());

        ChessBoard chessBoard = new ChessBoard(board);
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard);

        Assertions.assertThat(scoreCalculator.sumPiecesScore(Side.BLACK)).isEqualTo(4.0);
    }
}
