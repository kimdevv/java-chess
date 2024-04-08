package chess.domain.piece;

import static chess.domain.pixture.PieceFixture.BLACK_QUEEN;
import static chess.domain.pixture.PieceFixture.WHITE_BISHOP;
import static chess.domain.pixture.PieceFixture.WHITE_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BishopTest {
    /*
     * .......*
     * *.....*.
     * .*...*...
     * ..*.*...
     * ...k....
     * ..*.*...
     * .*...*..
     * *.....*.
     * */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7"})
    @DisplayName("비숍은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsEmpty(int file, int rank) {
        assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
    }

    /*
     * .......R
     * R.....R.
     * .R...R...
     * ..R.R...
     * ...k....
     * ..R.R...
     * .R...R..
     * R.....R.
     * */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7"})
    @DisplayName("비숍은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsOtherColor(int file, int rank) {
        assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece(),
                        Position.of(4, 4), WHITE_BISHOP.getPiece()))).isTrue();
    }

    /*
     * .......r
     * r.....r.
     * .r...r...
     * ..r.r...
     * ...k....
     * ..r.r...
     * .r...r..
     * r.....r.
     * */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7"})
    @DisplayName("비숍은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenTargetIsSameColor(int file, int rank) {
        assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(4, 4), WHITE_BISHOP.getPiece(),
                        Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
    }

    /*
     * .......r
     * ........
     * .....P...
     * ........
     * ...k....
     * ........
     * ........
     * ........
     * */
    @Test
    @DisplayName("비숍은 이동 경로에 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenPieceExistIn() {
        assertThat(WHITE_BISHOP.getPiece().canMove(Position.of(4, 4), Position.of(8, 8),
                Map.of(Position.of(6, 6), WHITE_QUEEN.getPiece()))).isFalse();
    }
}
