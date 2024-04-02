package chess.dao;

import static chess.dao.PieceMapper.BLACK_KING;
import static chess.domain.chesspiece.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.slidingPiece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceMapperTest {

    @Test
    @DisplayName("피스 정보를 통해 PieceMapper를 생성한다.")
    void PieceMapper_Create_from_piece() {
        Piece piece = new King(BLACK);
        var result = PieceMapper.from(piece);

        assertThat(result).isEqualTo(BLACK_KING);
    }

    @Test
    @DisplayName("타입과 팀을 통해 피스 정보를 가져온다.")
    void PieceMapper_Find_piece_from_type_and_team() {
        var result = PieceMapper.mapToPiece("KING", "BLACK");

        assertThat(result).isEqualTo(new King(BLACK));
    }
}
