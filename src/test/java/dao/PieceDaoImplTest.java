package dao;

import static org.assertj.core.api.Assertions.assertThat;
import static domain.Fixture.Positions.*;

import domain.game.PieceFactory;
import domain.game.PieceType;
import dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoImplTest {
    private final GameDao gameDao = new GameDaoImpl();
    private final PieceDao pieceDao = new PieceDaoImpl();
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DBConnector.getInstance().getConnection();
        connection.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    @DisplayName("여러 Piece 를 한 번에 저장하고 불러올 수 있다.")
    void addPieceBulkTest() {
        List<PieceDto> bulk = List.of(
                PieceDto.of(A1, PieceFactory.create(PieceType.BLACK_PAWN)),
                PieceDto.of(B2, PieceFactory.create(PieceType.WHITE_QUEEN))
        );
        int gameId = gameDao.addGame(connection);
        pieceDao.addAll(connection, bulk, gameId);

        List<PieceDto> result = pieceDao.findAllPieces(connection, gameId);

        assertThat(result).containsAll(bulk);
    }
}
