package chess.dto;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.fixture.PointFixture.포인트;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PieceDtoTest {

    @Test
    @DisplayName("dto로 변환할 수 있다.")
    void toDto() {
        final PieceDto pieceDto = PieceDto.of(포인트("A7"), new King(Team.WHITE));

        assertSoftly(softly -> {
            softly.assertThat(pieceDto.file()).isEqualTo("A");
            softly.assertThat(pieceDto.rank()).isEqualTo("7");
            softly.assertThat(pieceDto.team()).isEqualTo("WHITE");
            softly.assertThat(pieceDto.type()).isEqualTo("KING");
        });
    }

    @Test
    @DisplayName("dto에서 Point 정보를 알 수 있다.")
    void getPoint() {
        final PieceDto pieceDto = PieceDto.of(포인트("A7"), new King(Team.WHITE));

        final Point point = pieceDto.getPoint();

        assertThat(point).isEqualTo(포인트("A7"));
    }

    @Test
    @DisplayName("dto에서 Piece 정보를 알 수 있다.")
    void getPiece() {
        final PieceDto pieceDto = PieceDto.of(포인트("A7"), new King(Team.WHITE));

        final Piece piece = pieceDto.getPiece();

        assertThat(piece).isInstanceOf(King.class);
    }
}
