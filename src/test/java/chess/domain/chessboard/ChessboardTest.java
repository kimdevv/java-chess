package chess.domain.chessboard;

import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.FIVE;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.SIX;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.attribute.Color;
import chess.domain.attribute.Score;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessboardTest {

    @DisplayName("체스판에 King이 존재하는지 확인한다.")
    @Test
    void existKing() {
        Chessboard chessBoard = Chessboard.createChessBoard();
        chessBoard.move(Square.of(E, TWO), Square.of(E, THREE));
        chessBoard.move(Square.of(F, SEVEN), Square.of(F, SIX));
        chessBoard.move(Square.of(D, ONE), Square.of(H, FIVE));
        chessBoard.move(Square.of(H, FIVE), Square.of(E, EIGHT));

        assertTrue(chessBoard.catchKing());
    }

    /*
    .KR.....  8
    P.PB....  7
    .P..Q...  6
    ........  5
    .....nq.  4
    .......p  3
    .....pp.  2
    ....rk..  1

    abcdefgh

    black : K(0), Q(9), R(5), B(3), P(1), P(1), P(1) => 20
    white : k(0), q(9), r(5), n(2.5), p(1), p(1), p(1) => 19.5
     */
    @DisplayName("기물의 총 점수를 구한다.")
    @Test
    void totalScore() {
        Chessboard chessBoard = Chessboard.createChessBoard(
                Map.ofEntries(
                        Map.entry(Square.of(B, EIGHT), new King(Color.BLACK, Square.of(B, EIGHT))),
                        Map.entry(Square.of(E, SIX), new Queen(Color.BLACK, Square.of(E, SIX))),
                        Map.entry(Square.of(C, EIGHT), new Rook(Color.BLACK, Square.of(C, EIGHT))),
                        Map.entry(Square.of(D, SEVEN), new Bishop(Color.BLACK, Square.of(D, SEVEN))),
                        Map.entry(Square.of(A, SEVEN), new BlackPawn(Square.of(A, SEVEN))),
                        Map.entry(Square.of(C, SEVEN), new BlackPawn(Square.of(C, SEVEN))),
                        Map.entry(Square.of(B, SIX), new BlackPawn(Square.of(B, SIX))),

                        Map.entry(Square.of(F, ONE), new King(Color.WHITE, Square.of(F, ONE))),
                        Map.entry(Square.of(G, FOUR), new Queen(Color.WHITE, Square.of(G, FOUR))),
                        Map.entry(Square.of(E, ONE), new Rook(Color.WHITE, Square.of(E, ONE))),
                        Map.entry(Square.of(F, FOUR), new Knight(Color.WHITE, Square.of(F, FOUR))),
                        Map.entry(Square.of(F, TWO), new WhitePawn(Square.of(F, TWO))),
                        Map.entry(Square.of(G, TWO), new WhitePawn(Square.of(G, TWO))),
                        Map.entry(Square.of(H, THREE), new WhitePawn(Square.of(H, THREE)))
                )
        );

        assertAll(
                () -> assertThat(chessBoard.totalScoreOf(Color.BLACK)).isEqualTo(new Score(20)),
                () -> assertThat(chessBoard.totalScoreOf(Color.WHITE)).isEqualTo(new Score(19.5))
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    .....nq.  4
    .....p..  3
    .....p..  2
    ....rk..  1

    abcdefgh

    black : K(0), Q(9), R(5), B(3), P(1), P(1), P(1) => 20
    white : k(0), q(9), r(5), n(2.5), p(0.5), p(0.5) => 17.5
     */
    @DisplayName("같은 색의 폰이 세로로 있으면 각 폰은 0.5점으로 계산한다.")
    @Test
    void totalScoreOfPawn() {
        Chessboard chessBoard = Chessboard.createChessBoard(
                Map.ofEntries(
                        Map.entry(Square.of(F, ONE), new King(Color.WHITE, Square.of(F, ONE))),
                        Map.entry(Square.of(G, FOUR), new Queen(Color.WHITE, Square.of(G, FOUR))),
                        Map.entry(Square.of(E, ONE), new Rook(Color.WHITE, Square.of(E, ONE))),
                        Map.entry(Square.of(F, FOUR), new Knight(Color.WHITE, Square.of(F, FOUR))),
                        Map.entry(Square.of(F, TWO), new WhitePawn(Square.of(F, TWO))),
                        Map.entry(Square.of(F, THREE), new WhitePawn(Square.of(F, THREE)))
                ));

        assertAll(
                () -> assertThat(chessBoard.totalScoreOf(Color.BLACK)).isEqualTo(new Score(0)),
                () -> assertThat(chessBoard.totalScoreOf(Color.WHITE)).isEqualTo(new Score(17.5))
        );
    }

    /*
    .KR.....  8
    P.PB....  7
    .P..Q...  6
    ........  5
    .....nq.  4
    .....pp.  3
    .....p..  2
    ....rk..  1

    abcdefgh

    black : K(0), Q(9), R(5), B(3), P(1), P(1), P(1) => 20
    white : k(0), q(9), r(5), n(2.5), p(0.5), p(0.5), p(1) => 18.5
     */
    @DisplayName("세로로 있는 폰만 0.5점으로 계산하고 그 외의 폰은 1점으로 계산한다.")
    @Test
    void totalScoreOfSameFilePawn() {
        Chessboard chessBoard = Chessboard.createChessBoard(
                Map.ofEntries(
                        Map.entry(Square.of(B, EIGHT), new King(Color.BLACK, Square.of(B, EIGHT))),
                        Map.entry(Square.of(E, SIX), new Queen(Color.BLACK, Square.of(E, SIX))),
                        Map.entry(Square.of(C, EIGHT), new Rook(Color.BLACK, Square.of(C, EIGHT))),
                        Map.entry(Square.of(D, SEVEN), new Bishop(Color.BLACK, Square.of(D, SEVEN))),
                        Map.entry(Square.of(A, SEVEN), new BlackPawn(Square.of(A, SEVEN))),
                        Map.entry(Square.of(C, SEVEN), new BlackPawn(Square.of(C, SEVEN))),
                        Map.entry(Square.of(B, SIX), new BlackPawn(Square.of(B, SIX))),

                        Map.entry(Square.of(F, ONE), new King(Color.WHITE, Square.of(F, ONE))),
                        Map.entry(Square.of(G, FOUR), new Queen(Color.WHITE, Square.of(G, FOUR))),
                        Map.entry(Square.of(E, ONE), new Rook(Color.WHITE, Square.of(E, ONE))),
                        Map.entry(Square.of(F, FOUR), new Knight(Color.WHITE, Square.of(F, FOUR))),
                        Map.entry(Square.of(F, TWO), new WhitePawn(Square.of(F, TWO))),
                        Map.entry(Square.of(F, THREE), new WhitePawn(Square.of(F, THREE))),
                        Map.entry(Square.of(G, THREE), new WhitePawn(Square.of(G, THREE)))
                )
        );

        assertAll(
                () -> assertThat(chessBoard.totalScoreOf(Color.BLACK)).isEqualTo(new Score(20)),
                () -> assertThat(chessBoard.totalScoreOf(Color.WHITE)).isEqualTo(new Score(18.5))
        );
    }

    /*
    .KR.....  8
    P.PB....  7
    .P..Q...  6
    ........  5
    .....nq.  4
    .....pp.  3
    .....p..  2
    ....rk..  1

    abcdefgh

    black : K(0), Q(9), R(5), B(3), P(1), P(1), P(1) => 20
    white : k(0), q(9), r(5), n(2.5), p(0.5), p(0.5), p(1) => 18.5
     */
    @DisplayName("점수가 많은 쪽이 우승자다.")
    @Test
    void findWinner() {
        Chessboard chessBoard = Chessboard.createChessBoard(
                Map.ofEntries(
                        Map.entry(Square.of(B, EIGHT), new King(Color.BLACK, Square.of(B, EIGHT))),
                        Map.entry(Square.of(E, SIX), new Queen(Color.BLACK, Square.of(E, SIX))),
                        Map.entry(Square.of(C, EIGHT), new Rook(Color.BLACK, Square.of(C, EIGHT))),
                        Map.entry(Square.of(D, SEVEN), new Bishop(Color.BLACK, Square.of(D, SEVEN))),
                        Map.entry(Square.of(A, SEVEN), new BlackPawn(Square.of(A, SEVEN))),
                        Map.entry(Square.of(C, SEVEN), new BlackPawn(Square.of(C, SEVEN))),
                        Map.entry(Square.of(B, SIX), new BlackPawn(Square.of(B, SIX))),

                        Map.entry(Square.of(F, ONE), new King(Color.WHITE, Square.of(F, ONE))),
                        Map.entry(Square.of(G, FOUR), new Queen(Color.WHITE, Square.of(G, FOUR))),
                        Map.entry(Square.of(E, ONE), new Rook(Color.WHITE, Square.of(E, ONE))),
                        Map.entry(Square.of(F, FOUR), new Knight(Color.WHITE, Square.of(F, FOUR))),
                        Map.entry(Square.of(F, TWO), new WhitePawn(Square.of(F, TWO))),
                        Map.entry(Square.of(F, THREE), new WhitePawn(Square.of(F, THREE))),
                        Map.entry(Square.of(G, THREE), new WhitePawn(Square.of(G, THREE)))
                )
        );

        assertThat(chessBoard.findWinner()).isEqualTo(GameResult.BLACK_WIN);
    }

    @DisplayName("왕이 잡힌 경우 잡은 쪽이 우승자다.")
    @Test
    void findWinnerInCaseOfCatchingKing() {
        Chessboard chessBoard = Chessboard.createChessBoard();
        chessBoard.move(Square.of(E, TWO), Square.of(E, THREE));
        chessBoard.move(Square.of(F, SEVEN), Square.of(F, SIX));
        chessBoard.move(Square.of(D, ONE), Square.of(H, FIVE));
        chessBoard.move(Square.of(H, FIVE), Square.of(E, EIGHT));

        assertThat(chessBoard.findWinner()).isEqualTo(GameResult.WHITE_WIN);
    }
}
