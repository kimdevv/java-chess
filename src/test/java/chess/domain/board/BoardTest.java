package chess.domain.board;

import chess.domain.game.GameCreator;
import chess.domain.game.InitialGameCreator;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.route.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.fixture.PieceFixture.BLACK_BISHOP;
import static chess.fixture.PieceFixture.BLACK_KING;
import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.BLACK_QUEEN;
import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PieceFixture.EMPTY;
import static chess.fixture.PieceFixture.WHITE_KING;
import static chess.fixture.PieceFixture.WHITE_KNIGHT;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PieceFixture.WHITE_QUEEN;
import static chess.fixture.PieceFixture.WHITE_ROOK;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.A5;
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
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.G2;
import static chess.fixture.PositionFixture.G4;
import static chess.fixture.PositionFixture.H3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BoardTest {

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
    @DisplayName("경로에 있는 기물을 찾는다.")
    @Test
    void findPieces() {
        Board board = Board.from(new InitialGameCreator());
        Path path = new Path(List.of(A7, A6, A5, A4, A3, A2));

        List<Piece> pieces = board.findPieces(path);

        assertThat(pieces).isEqualTo(List.of(BLACK_PAWN, EMPTY, EMPTY, EMPTY, EMPTY, WHITE_PAWN));
    }

    @DisplayName("기물을 source 위치에서 target 위치로 옮긴다.")
    @Test
    void move() {
        Board board = Board.from(new InitialGameCreator());

        board.move(B2, B3);

        assertAll(
                () -> assertThat(board.findPiece(B2)).isEqualTo(EMPTY),
                () -> assertThat(board.findPiece(B3)).isEqualTo(WHITE_PAWN)
        );
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
    @DisplayName("주어진 File에 남아 있는 검은색 말에 대한 점수를 구한다.")
    @ParameterizedTest
    @CsvSource(value = {"A, 1", "B, 1", "C, 6", "D, 3", "E, 9", "F, 0", "G, 0", "H, 0"})
    void blackPiecesScoreInFile(File file, double expected) {
        Board board = Board.from(new GameCreator() {
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

        double actual = board.calculateSameSidePiecesScoreInFile(Side.BLACK, file);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주어진 File에 남아 있는 흰색 말에 대한 점수를 구한다.")
    @ParameterizedTest
    @CsvSource(value = {"A, 0", "B, 0", "C, 0", "D, 0", "E, 5", "F, 3.5", "G, 10", "H, 1"})
    void whitePiecesScoreInFile(File file, double expected) {
        Board board = Board.from(new GameCreator() {
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

        double actual = board.calculateSameSidePiecesScoreInFile(Side.WHITE, file);

        assertThat(actual).isEqualTo(expected);
    }
}
