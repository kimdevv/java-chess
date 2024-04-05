package chess.domain.board;

import static chess.fixture.PieceFixture.BLACK_BISHOP;
import static chess.fixture.PieceFixture.BLACK_KING;
import static chess.fixture.PieceFixture.BLACK_KNIGHT;
import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.BLACK_QUEEN;
import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PieceFixture.WHITE_BISHOP;
import static chess.fixture.PieceFixture.WHITE_KING;
import static chess.fixture.PieceFixture.WHITE_KNIGHT;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PieceFixture.WHITE_QUEEN;
import static chess.fixture.PieceFixture.WHITE_ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BoardStatusTest {

    @DisplayName("Target이 King이면 KING_DEAD이다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK", "WHITE"})
    void kingDeadIfTargetIsKing(Side side) {
        BoardStatus boardStatus = BoardStatus.of(WHITE_PAWN, new King(side));

        assertThat(boardStatus).isEqualTo(BoardStatus.KING_DEAD);
    }

    @DisplayName("Source가 흰색 진영이면 BLACK_TURN이다.")
    @ParameterizedTest
    @MethodSource("whitePieces")
    void blackTurnIfSourceIsWhite(Piece piece) {
        BoardStatus boardStatus = BoardStatus.of(piece, BLACK_PAWN);

        assertThat(boardStatus).isEqualTo(BoardStatus.BLACK_TURN);
    }

    private static Stream<Arguments> whitePieces() {
        return Stream.of(
                Arguments.arguments(WHITE_ROOK),
                Arguments.arguments(WHITE_KNIGHT),
                Arguments.arguments(WHITE_BISHOP),
                Arguments.arguments(WHITE_QUEEN),
                Arguments.arguments(WHITE_KING),
                Arguments.arguments(WHITE_PAWN)
        );
    }

    @DisplayName("Source가 검은색 진영이면 WHITE_TURN이다.")
    @ParameterizedTest
    @MethodSource("blackPieces")
    void whiteTurnIfSourceIsBlack(Piece piece) {
        BoardStatus boardStatus = BoardStatus.of(piece, WHITE_PAWN);

        assertThat(boardStatus).isEqualTo(BoardStatus.WHITE_TURN);
    }

    private static Stream<Arguments> blackPieces() {
        return Stream.of(
                Arguments.arguments(BLACK_ROOK),
                Arguments.arguments(BLACK_KNIGHT),
                Arguments.arguments(BLACK_BISHOP),
                Arguments.arguments(BLACK_QUEEN),
                Arguments.arguments(BLACK_KING),
                Arguments.arguments(BLACK_PAWN)
        );
    }
}
