package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import chess.fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveJdbcDaoTest {
    private final MoveDao moveDao = new MoveJdbcDao();

    @BeforeEach
    void setUp() {
        moveDao.deleteAll();
    }

    @AfterEach
    void rollback() {
        moveDao.deleteAll();
    }

    @DisplayName("moveDao를 조회할 수 있다.")
    @Test
    void findAll() {
        moveDao.save(MoveRequest.of(PositionFixture.A2, PositionFixture.A4));
        moveDao.save(MoveRequest.of(PositionFixture.A7, PositionFixture.A5));

        List<MoveResponse> moveResponses = moveDao.findAll();

        List<Move> moves = moveResponses.stream()
                .map(MoveResponse::from)
                .toList();

        assertThat(moves).containsExactly(new Move(PositionFixture.A2, PositionFixture.A4),
                new Move(PositionFixture.A7, PositionFixture.A5));
    }
}
