package chess.dao;

import chess.dto.PieceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    private PieceDao pieceDao = new PieceSimpleDao();

    @BeforeEach
    void setUp() {
        pieceDao.deleteAll();
    }

    @AfterEach
    void rollback() {
        pieceDao.deleteAll();
    }

    @Test
    @DisplayName("피스를 저장하고 찾을 수 있다.")
    void findOne() {
        final PieceDto pieceDto = new PieceDto("A", "3", "WHITE", "PAWN");
        pieceDao.save(pieceDto);

        PieceDto findPiece = pieceDao.findOne("A", "3");

        assertThat(findPiece.team()).isEqualTo("WHITE");
        assertThat(findPiece.type()).isEqualTo("PAWN");
    }

    @Test
    @DisplayName("저장된 피스를 모두 찾을 수 있다.")
    void findAll() {
        final PieceDto pieceDtoA = new PieceDto("A", "3", "WHITE", "PAWN");
        final PieceDto pieceDtoB = new PieceDto("B", "5", "BLACK", "PAWN");
        final PieceDto pieceDtoC = new PieceDto("C", "7", "BLACK", "KING");
        pieceDao.save(pieceDtoA);
        pieceDao.save(pieceDtoB);
        pieceDao.save(pieceDtoC);

        assertThat(pieceDao.findAll())
                .containsExactly(pieceDtoA, pieceDtoB, pieceDtoC);
    }

    @Test
    @DisplayName("저장된 피스가 있는지 판별할 수 있다.")
    void hasRecords() {
        final PieceDto pieceDtoA = new PieceDto("A", "3", "WHITE", "PAWN");
        final PieceDto pieceDtoB = new PieceDto("B", "5", "BLACK", "PAWN");
        final PieceDto pieceDtoC = new PieceDto("C", "7", "BLACK", "KING");
        pieceDao.save(pieceDtoA);
        pieceDao.save(pieceDtoB);
        pieceDao.save(pieceDtoC);

        assertThat(pieceDao.hasRecords()).isTrue();
    }
}
