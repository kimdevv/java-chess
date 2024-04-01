package chess.dao;

import chess.domain.position.Position;
import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDaoImplTest {
    private final MoveDao moveDao = new FakeMoveDaoImpl();

    @DisplayName("새로운 이동 정보를 저장할 수 있다")
    @Test
    void saveMove() {
        Position source = Position.from("a2");
        Position target = Position.from("a4");
        moveDao.save(new MoveRequest(source.getValue(), target.getValue(), 1L));

        Position source2 = Position.from("b2");
        Position target2 = Position.from("b4");
        moveDao.save(new MoveRequest(source2.getValue(), target2.getValue(), 1L));

        List<MoveResponse> moves = moveDao.findAll(1L);
        Assertions.assertThat(moves).hasSize(2);
    }

    @DisplayName("특정 체스 게임의 모든 이동 정보를 찾을 수 있다")
    @Test
    void findAllMovesByGameId() {
        MoveRequest moveRequest1 = new MoveRequest("e2", "e4", 1L);
        MoveRequest moveRequest2 = new MoveRequest("e7", "e5", 1L);
        moveDao.save(moveRequest1);
        moveDao.save(moveRequest2);

        List<MoveResponse> moves = moveDao.findAll(1L);

        Assertions.assertThat(moves)
                .hasSize(2)
                .extracting(MoveResponse::source)
                .containsExactly("e2", "e7");

        Assertions.assertThat(moves)
                .extracting(MoveResponse::target)
                .containsExactly("e4", "e5");
    }

    static class FakeMoveDaoImpl implements MoveDao {
        private final List<MoveResponse> moves = new ArrayList<>();
        private long currentId = 1;

        @Override
        public void save(MoveRequest moveRequest) {
            moves.add(new MoveResponse(currentId++, moveRequest.source(), moveRequest.target(),
                    moveRequest.chessGameId()));
        }

        @Override
        public List<MoveResponse> findAll(Long id) {
            return moves.stream()
                    .filter(move -> move.chessGameId().equals(id))
                    .collect(Collectors.toList());
        }
    }
}
