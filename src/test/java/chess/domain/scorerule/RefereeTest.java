package chess.domain.scorerule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    @DisplayName("팀별 피스 목록을 점수 규칙에 맞게 점수를 계산한다.")
    void calculateScore() {
        Map<Position, Piece> piecePosition = new HashMap<>();
        Map<Position, Piece> whiteTeam = Map.of(
                Position.of(4, 2), new Queen(Color.WHITE), // 9
                Position.of(1, 2), new Pawn(Color.WHITE), // 0.5
                Position.of(1, 3), new Pawn(Color.WHITE), // 0.5
                Position.of(2, 2), new Pawn(Color.WHITE)); // 1
        piecePosition.putAll(whiteTeam);

        Map<Position, Piece> blackTeam = Map.of(
                Position.of(1, 8), new Queen(Color.BLACK), // 9
                Position.of(2, 4), new King(Color.BLACK), // 0
                Position.of(3, 4), new Knight(Color.BLACK), // 2.5
                Position.of(4, 4), new Bishop(Color.BLACK)); // 3
        piecePosition.putAll(blackTeam);

        Referee referee = new Referee(piecePosition);
        assertAll(
                () -> assertThat(referee.calculateScore(Color.WHITE)).isEqualTo(11),
                () -> assertThat(referee.calculateScore(Color.BLACK)).isEqualTo(14.5));
    }
}
