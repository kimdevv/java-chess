package chess.domain.route;

import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PieceFixture.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RouteTest {

    @DisplayName("경로에 존재하는 모든 기물이 EMPTY는 아니다.")
    @Test
    void notAllPathPiecesEmpty() {
        Route route = new Route(List.of(EMPTY, BLACK_ROOK), EMPTY);

        boolean actual = route.hasNonEmptyPathPieces();

        assertThat(actual).isTrue();
    }

    @DisplayName("경로에 존재하는 모든 기물이 EMPTY이다.")
    @Test
    void allPathPiecesEmpty() {
        Route route = new Route(List.of(EMPTY, EMPTY), EMPTY);

        boolean actual = route.hasNonEmptyPathPieces();

        assertThat(actual).isFalse();
    }
}
