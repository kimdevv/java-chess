package chess.domain.chesspiece;

import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들을 반환한다.")
    void Knight_Check_route() {
        Piece piece = new Knight(WHITE);
        List<Position> route = piece.findRoute(new Position("a", "1"), new Position("b", "3"),
                true);
        List<Position> positions = List.of();
        assertThat(route).isEqualTo(positions);
    }

    @Test
    @DisplayName("목적지 제외 갈 수 있는 위치들이 아니면 예외를 발생한다.")
    void Knight_Validate_route() {
        Piece piece = new Knight(WHITE);
        assertThatThrownBy(() ->
            piece.findRoute(new Position("a", "1"), new Position("b", "4"), true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자신이 폰인지 확인한다.")
    void Knight_Check_pawn() {
        Piece piece = new Knight(WHITE);
        assertThat(piece.isPawn()).isFalse();
    }
}
