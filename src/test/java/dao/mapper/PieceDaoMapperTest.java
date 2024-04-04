package dao.mapper;

import domain.Side;
import domain.piece.Piece;
import domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoMapperTest {

    @DisplayName("db 데이터를 piece로 만든다")
    @Test
    void dataToPiece() {
        Piece piece = PieceDaoMapper.dataToPiece("r");
        assertThat(piece).isEqualTo(new Rook(Side.WHITE));
    }

    @DisplayName("piece를 db 데이터로 만든다")
    @Test
    void pieceToData() {
        assertThat(PieceDaoMapper.dataToPiece("R")).isEqualTo(new Rook(Side.BLACK));
    }
}
