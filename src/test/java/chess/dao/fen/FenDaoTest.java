package chess.dao.fen;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import chess.fen.Fen;
import chess.fen.dao.FenDao;

class FenDaoTest {

    private static final int ID = 1;

    private final FenDao fenDao = new FenDao();

    @AfterEach
    void clean() {
        fenDao.delete();
    }

    @Test
    void getConnection() throws SQLException {
        try (final var connection = fenDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void find() {
        fenDao.save(ID, new Fen("4k2r/6r1/8/8/8/8/3R4/R3K3"));
        Fen fen = fenDao.find(ID);
        assertThat(fen.value()).isEqualTo("4k2r/6r1/8/8/8/8/3R4/R3K3");
    }

    @Test
    void save() {
        Fen fen = new Fen("8/8/8/4p1K1/2k1P3/8/8/8");
        fenDao.save(ID, fen);
        Fen expected = fenDao.find(ID);
        assertThat(fen).isEqualTo(expected);
    }

    @Test
    void update() {
        fenDao.save(ID, new Fen("8/8/8/4p1K1/2k1P3/8/8/8"));
        Fen fen = new Fen("4k2r/6r1/8/8/8/8/3R4/R3K3");
        fenDao.update(ID, fen);
        Fen expected = fenDao.find(ID);
        assertThat(fen).isEqualTo(expected);
    }

    @Test
    void delete() {
        fenDao.delete();
        assertThat(fenDao.find(ID)).isNull();
    }

    @Test
    void exists() {
        assertThat(fenDao.exists(ID)).isFalse();
        fenDao.save(ID, new Fen("8/8/8/4p1K1/2k1P3/8/8/8"));
        assertThat(fenDao.exists(ID)).isTrue();
    }
}
