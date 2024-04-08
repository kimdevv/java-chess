package chess.domain.piece;

import static chess.domain.pixture.PieceFixture.BLACK_QUEEN;
import static chess.domain.pixture.PieceFixture.WHITE_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {

    /*
     * ...E...E
     * E..E..E.
     * .E.E.E...
     * ..EEE...
     * EEEqEEEE
     * ..EEE...
     * .E.E.E..
     * E..E..E.
     * */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7",
            "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
    @DisplayName("퀸은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsEmpty(int file, int rank) {
        assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(file, rank), Map.of())).isTrue();
    }

    /*
     * ...R...R
     * R..R..R.
     * .R.R.R...
     * ..RRR...
     * RRRqRRRR
     * ..RRR...
     * .R.R.R..
     * R..R..R.
     * */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7",
            "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
    @DisplayName("퀸은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void canMoveWhenTargetIsOtherColor(int file, int rank) {
        assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(file, rank), BLACK_QUEEN.getPiece()))).isTrue();
    }

    /*
     * ...r...r
     * r..r..r.
     * .r.r.r...
     * ..rrr...
     * rrrqrrrr
     * ..rrr...
     * .r.r.r..
     * r..r..r.
     * */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8",
            "7,1", "6,2", "3,5", "2,6", "1,7",
            "8,4", "7,4", "6,4", "5,4", "3,4", "2,4", "1,4",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8"})
    @DisplayName("퀸은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenTargetIsSameColor(int file, int rank) {
        assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(file, rank),
                Map.of(Position.of(4, 4), WHITE_QUEEN.getPiece(),
                        Position.of(file, rank), WHITE_QUEEN.getPiece()))).isFalse();
    }

    /*
     * .......Q
     * ........
     * .....R..
     * ........
     * ...q....
     * ........
     * ........
     * ........
     * */
    @Test
    @DisplayName("퀸은 이동 경로에 말이 있는 경우 이동할 수 없다.")
    void canNotMoveWhenPieceExistIn() {
        assertThat(WHITE_QUEEN.getPiece().canMove(Position.of(4, 4), Position.of(8, 8),
                Map.of(Position.of(6, 6), WHITE_QUEEN.getPiece()))).isFalse();
    }
}
