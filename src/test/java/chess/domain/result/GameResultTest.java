package chess.domain.result;

import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.B8;
import static chess.domain.Fixtures.C7;
import static chess.domain.Fixtures.C8;
import static chess.domain.Fixtures.D7;
import static chess.domain.Fixtures.F2;
import static chess.domain.Fixtures.F3;
import static chess.domain.Fixtures.F4;
import static chess.domain.Fixtures.H3;
import static chess.domain.Fixtures.H4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    //abcdefgh
    //.KR..... - 8
    //P.PB.... - 7
    //........ - 6
    //........ - 5
    //.....n.p - 4
    //.....p.p - 3
    //.....k.. - 2
    //........ - 1
    //

    private static final Board GIVEN_BOARD = new Board(
            Map.of(
                    B8, Piece.of(PieceType.KING, Color.BLACK),
                    C8, Piece.of(PieceType.ROOK, Color.BLACK),
                    A7, Pawn.of(Color.BLACK),
                    C7, Pawn.of(Color.BLACK),
                    D7, Piece.of(PieceType.BISHOP, Color.BLACK),
                    F4, Piece.of(PieceType.KNIGHT, Color.WHITE),
                    H4, Pawn.of(Color.WHITE),
                    F3, Pawn.of(Color.WHITE),
                    H3, Pawn.of(Color.WHITE),
                    F2, Piece.of(PieceType.KING, Color.WHITE)
            ));

    @Test
    @DisplayName("각 체스 색상의 점수를 계산한다.")
    void Given_GameResultWithBoard_When_CalculateScore_Then_ReturnScore() {
        //given
        GameResult gameResult = new GameResult(GIVEN_BOARD);
        //when, then
        assertAll(
                () -> assertThat(gameResult.calcuateScore(Color.WHITE)).isEqualTo(new Score(4.5)),
                () -> assertThat(gameResult.calcuateScore(Color.BLACK)).isEqualTo(new Score(10))
        );
    }

    @Test
    @DisplayName("승부가 나지 않은 채 검은색 점수가 흰색보다 높으면 검은색을 반환한다.")
    void Given_GameResultWithBoard_When_getWinnerColorBlackScoreGraterThanWhite_Then_ReturnBlackColor() {
        //given
        Board board = new Board(
                Map.of(
                        B8, Piece.of(PieceType.KING, Color.BLACK),
                        H3, Pawn.of(Color.WHITE),
                        F2, Piece.of(PieceType.KING, Color.WHITE)
                ));
        GameResult gameResult = new GameResult(board);
        //when, then
        assertThat(gameResult.getWinnerColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("승부가 나지 않은 채 흰색 점수가 검은색보다 높으면 흰색을 반환한다.")
    void Given_GameResultWithBoard_When_getWinnerColorWhiteScoreGraterThanBlack_Then_ReturnWhiteColor() {
        //given
        Board board = new Board(
                Map.of(
                        B8, Piece.of(PieceType.KING, Color.BLACK),
                        F4, Piece.of(PieceType.ROOK, Color.WHITE),
                        F2, Piece.of(PieceType.KING, Color.WHITE)
                ));
        GameResult gameResult = new GameResult(board);
        //when, then
        assertThat(gameResult.getWinnerColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("비기고 있는 상황에서는 색이 없음을 반환한다.")
    void Given_GameResultWithInitialBoardWhen_getWinnerColor_Then_ReturnNoneColor() {
        //given
        GameResult gameResult = new GameResult(BoardFactory.create());
        //when, then
        assertThat(gameResult.getWinnerColor()).isEqualTo(Color.NONE);
    }

    @Test
    @DisplayName("점수 격차가 있더라도 상대편 킹이 죽으면 이긴 색상을 반환한다.")
    void Given_GameResultWithAloneKing_getWinnerColor_Then_ReturnExistKingColor() {
        //given
        Board board = new Board(Map.of(B8, Piece.of(PieceType.KING, Color.BLACK), F3, Pawn.of(Color.WHITE)));
        GameResult gameResult = new GameResult(board);
        //when, then
        assertThat(gameResult.getWinnerColor()).isEqualTo(Color.BLACK);
    }
}
