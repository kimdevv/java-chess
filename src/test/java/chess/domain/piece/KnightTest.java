package chess.domain.piece;

import static chess.domain.pixture.PieceFixture.BLACK_QUEEN;
import static chess.domain.pixture.PieceFixture.WHITE_KNIGHT;
import static chess.domain.pixture.PieceFixture.WHITE_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    /*
     * ........
     * ........
     * ..E.E...
     * .E...E..
     * ...k....
     * .E...E..
     * ..E.E...
     * ........
     * */
    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("나이트는 도착 위치가 비어있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsEmpty(int file, int rank) {
        assertThat(WHITE_KNIGHT.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(4, 4), WHITE_KNIGHT.getPiece()))).isTrue();
    }

    /*
     * ........
     * ........
     * ..*.*...
     * .*...*..
     * ...k....
     * .*...*..
     * ..*.*...
     * ........
     * */
    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("나이트는 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsOtherColor(int file, int rank) {
        assertThat(WHITE_KNIGHT.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(4, 4), WHITE_KNIGHT.getPiece(),
                        Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
    }

    /*
     * ........
     * ........
     * ..k.k...
     * .k...k..
     * ...k....
     * .k...k..
     * ..k.k...
     * ........
     * */
    @ParameterizedTest
    @CsvSource({"5,6", "5,2", "3,6", "3,2", "6,5", "6,3", "2,5", "2,3"})
    @DisplayName("나이트는 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenTargetIsSameColor(int file, int rank) {
        assertThat(WHITE_KNIGHT.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(4, 4), WHITE_KNIGHT.getPiece(),
                        Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
    }
}
