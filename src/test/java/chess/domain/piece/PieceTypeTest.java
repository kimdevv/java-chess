package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @Test
    @DisplayName("킹은 점수가 없다.")
    void findScoreKing() {
        assertAll(
                () -> assertThat(PieceType.KING.findScore(List.of(Position.of(1, 2), Position.of(2, 2)))).isEqualTo(0),
                () -> assertThat(PieceType.KING.findScore(List.of(Position.of(1, 2))))
                        .isEqualTo(0));
    }

    @Test
    @DisplayName("퀸은 피스의 수별 9점을 받는다.")
    void findScoreQueen() {
        assertAll(
                () -> assertThat(PieceType.QUEEN.findScore(List.of())).isEqualTo(0),
                () -> assertThat(PieceType.QUEEN.findScore(List.of(Position.of(1, 2)))).isEqualTo(9),
                () -> assertThat(PieceType.QUEEN.findScore(List.of(Position.of(1, 2), Position.of(1, 2))))
                        .isEqualTo(18));
    }

    @Test
    @DisplayName("비숍은 피스의 수별 3점을 받는다.")
    void findScoreBishop() {
        assertAll(
                () -> assertThat(PieceType.BISHOP.findScore(List.of())).isEqualTo(0),
                () -> assertThat(PieceType.BISHOP.findScore(List.of(Position.of(1, 2)))).isEqualTo(3),
                () -> assertThat(PieceType.BISHOP.findScore(List.of(Position.of(1, 2), Position.of(1, 2))))
                        .isEqualTo(6));
    }

    @Test
    @DisplayName("룩은 피스의 수별 3점을 받는다.")
    void findScoreRook() {
        assertAll(
                () -> assertThat(PieceType.ROOK.findScore(List.of())).isEqualTo(0),
                () -> assertThat(PieceType.ROOK.findScore(List.of(Position.of(1, 2)))).isEqualTo(5),
                () -> assertThat(PieceType.ROOK.findScore(List.of(Position.of(1, 2), Position.of(1, 2))))
                        .isEqualTo(10));
    }

    @Test
    @DisplayName("나이트는 피스의 수별 2.5점을 받는다.")
    void findScoreKnight() {
        assertAll(
                () -> assertThat(PieceType.KNIGHT.findScore(List.of())).isEqualTo(0),
                () -> assertThat(PieceType.KNIGHT.findScore(List.of(Position.of(1, 2)))).isEqualTo(2.5),
                () -> assertThat(PieceType.KNIGHT.findScore(List.of(Position.of(1, 2), Position.of(1, 2))))
                        .isEqualTo(5));
    }

    @Nested
    class pawnScore {

        @Test
        @DisplayName("같은 세로 줄에 같은 색의 폰이 여러개 있는 경우, 각각 0.5점")
        void findScorePawn() {
            double score = PieceType.PAWN.findScore(List.of(
                    Position.of(1, 2),
                    Position.of(1, 3)
            ));

            assertThat(score).isEqualTo(1);
        }

        @Test
        @DisplayName("같은 세로 줄에 같은 색의 폰이 하나만 있는 경우, 1점(기본점수)")
        void findScoreSameFilePawn() {
            double score = PieceType.PAWN.findScore(List.of(
                    Position.of(1, 2),
                    Position.of(2, 2)
            ));

            assertThat(score).isEqualTo(2);
        }
    }
}
