package domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessPieceMapperTest {

    @DisplayName("Blank를 제외한 모든 말의 인스턴스를 얻는다.")
    @ParameterizedTest
    @MethodSource("providePieceTypeExceptBlank")
    void getInstanceExceptBlank(PieceType type) {
        Color white = Color.WHITE;
        Color black = Color.BLACK;

        ChessPiece whiteInstance = ChessPieceMapper.getInstance(type, white);
        ChessPiece blackInstance = ChessPieceMapper.getInstance(type, black);

        assertThat(whiteInstance.getColor()).isEqualTo(white);
        assertThat(whiteInstance.getType()).isEqualTo(type);

        assertThat(blackInstance.getColor()).isEqualTo(black);
        assertThat(blackInstance.getType()).isEqualTo(type);

    }

    @DisplayName("Blank 말을 얻는다.")
    @Test
    void getBlankInstance() {
        PieceType type = PieceType.BLANK;
        Color color = Color.ANY;

        ChessPiece instance = ChessPieceMapper.getInstance(type, color);

        assertThat(instance).isEqualTo(Blank.getInstance());
    }

    private static Stream<Arguments> providePieceTypeExceptBlank() {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType != PieceType.BLANK)
                .map(Arguments::arguments);
    }
}
