package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @Test
    @DisplayName("비어있는 말을 생성하면 예외가 발생한다.")
    void Given_Piece_When_CreatedWithEmptyPieceType_Then_Exception() {
        //given, when, then
        assertThatThrownBy(() -> Piece.of(PieceType.EMPTY, Color.NONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 타입과 색상으로 체스말을 생성할 수 없습니다.");
    }

    @Test
    @DisplayName("색상이 다른 타입의 Pawn을 생성하면 예외가 발생한다.")
    void Given_Piece_When_CreatedWithDifferentColorWithPawnColor_Then_Exception() {
        assertAll(
                () -> assertThatThrownBy(() -> Piece.of(PieceType.WHITE_PAWN, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 타입과 색상으로 체스말을 생성할 수 없습니다."),
                () -> assertThatThrownBy(() -> Piece.of(PieceType.BLACK_PAWN, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 타입과 색상으로 체스말을 생성할 수 없습니다.")
        );
    }
}
