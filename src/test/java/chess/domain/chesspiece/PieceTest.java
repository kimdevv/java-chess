package chess.domain.chesspiece;

import static chess.domain.GameStatus.WHITE_TURN;
import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.slidingPiece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("자신의 턴이 아닐 때는 게임을 진행할 수 없다.")
    void Piece_Cannot_move_not_my_turn() {
        var sut = new King(BLACK);

        assertThatThrownBy(() -> sut.checkValidMove(WHITE_TURN))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 팀인지 확인한다.")
    void Piece_Validate_team() {
        var sut = new King(WHITE);
        assertThat(sut.isTeam(new King(WHITE))).isTrue();
        assertThat(sut.isTeam(new King(BLACK))).isFalse();
    }
}
