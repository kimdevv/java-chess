package chess.domain.game;

import static chess.fixture.PieceFixture.BLACK_BISHOP;
import static chess.fixture.PieceFixture.BLACK_KING;
import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.BLACK_QUEEN;
import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PieceFixture.WHITE_KING;
import static chess.fixture.PieceFixture.WHITE_KNIGHT;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PieceFixture.WHITE_QUEEN;
import static chess.fixture.PieceFixture.WHITE_ROOK;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A6;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B6;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.C7;
import static chess.fixture.PositionFixture.C8;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E6;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.G2;
import static chess.fixture.PositionFixture.G4;
import static chess.fixture.PositionFixture.H3;
import static chess.fixture.PositionFixture.H5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.BoardStatus;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    /*
    RNBQKBNR  8
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1

    abcdefgh
     */
    @DisplayName("검은색 진영이 먼저 턴을 진행하면 예외가 발생한다.")
    @Test
    void notProceedBlackTurn() {
        Game game = new Game(new InitialGameCreator());

        assertThatThrownBy(() -> game.proceedTurn(A7, A6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 진영의 차례가 아닙니다.");
    }

    @DisplayName("흰색 진영이 먼저 턴을 진행한다.")
    @Test
    void proceedWhiteTurn() {
        Game game = new Game(new InitialGameCreator());

        assertThatCode(() -> game.proceedTurn(A2, A3))
                .doesNotThrowAnyException();
    }

    /*
    RNBQKBNR  8
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    p.......  3
    .ppppppp  2
    rnbqkbnr  1

    abcdefgh
     */
    @DisplayName("흰색 진영 다음에 흰색 진영이 턴을 진행하면 예외가 발생한다.")
    @Test
    void notProceedWhiteTurn() {
        Game game = new Game(new InitialGameCreator());
        game.proceedTurn(A2, A3);

        assertThatThrownBy(() -> game.proceedTurn(B2, B3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 진영의 차례가 아닙니다.");
    }

    @DisplayName("흰색 진영 다음에 검은색 진영이 턴을 진행한다.")
    @Test
    void proceedBlackTurn() {
        Game game = new Game(new InitialGameCreator());
        game.proceedTurn(A2, A3);

        assertThatCode(() -> game.proceedTurn(A7, A6))
                .doesNotThrowAnyException();
    }

    /*
    ....K...  8
    ........  7
    ........  6
    .......q  5
    ........  4
    ........  3
    ........  2
    ........  1

    abcdefgh
     */
    @DisplayName("King을 잡다.")
    @Test
    void catchKing() {
        Game game = new Game(new GameCreator() {
            @Override
            public Map<Position, Piece> createBoard() {
                return new HashMap<>() {{
                    put(E8, BLACK_KING);
                    put(H5, WHITE_QUEEN);
                }};
            }

            @Override
            public BoardStatus createBoardStatus() {
                return BoardStatus.WHITE_TURN;
            }
        });

        game.proceedTurn(H5, E8);

        assertThat(game.isNotKingDead()).isFalse();
    }

    /*
    .KR.....  8
    P.PB....  7
    .P..Q...  6
    ........  5
    .....nq.  4
    .....p.p  3
    .....pp.  2
    ....rk..  1

    abcdefgh
     */
    @DisplayName("현재까지 남아 있는 말에 따라 점수를 계산한다.")
    @Test
    void calculateScore() {
        Game game = new Game(new GameCreator() {
            @Override
            public Map<Position, Piece> createBoard() {
                return new HashMap<>() {{
                    put(B8, BLACK_KING);
                    put(C8, BLACK_ROOK);
                    put(A7, BLACK_PAWN);
                    put(C7, BLACK_PAWN);
                    put(D7, BLACK_BISHOP);
                    put(B6, BLACK_PAWN);
                    put(E6, BLACK_QUEEN);
                    put(F4, WHITE_KNIGHT);
                    put(G4, WHITE_QUEEN);
                    put(F3, WHITE_PAWN);
                    put(H3, WHITE_PAWN);
                    put(F2, WHITE_PAWN);
                    put(G2, WHITE_PAWN);
                    put(E1, WHITE_ROOK);
                    put(F1, WHITE_KING);
                }};
            }

            @Override
            public BoardStatus createBoardStatus() {
                return BoardStatus.WHITE_TURN;
            }
        });

        Score score = game.calculateScore();

        assertAll(
                () -> assertThat(score.blackScore()).isEqualTo(20),
                () -> assertThat(score.whiteScore()).isEqualTo(19.5)
        );
    }
}
