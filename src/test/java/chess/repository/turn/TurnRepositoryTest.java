package chess.repository.turn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Color;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnRepositoryTest {

    private TurnRepository turnRepository;

    @BeforeEach
    void init() {
        turnRepository = new FakeTurnRepository();
    }

    @Test
    @DisplayName("색상의 문자열 값을 저장한다.")
    void addPiece() {
        assertThatCode(() -> turnRepository.save(Color.BLACK.name()));
    }

    @Test
    @DisplayName("하나의 Turn을 조회한다.")
    void findOne() {
        turnRepository.save(Color.BLACK.name());
        assertThat(turnRepository.findAny()).isEqualTo(Optional.of(Color.BLACK));
    }

    @Test
    @DisplayName("하나의 Turn을 조회 시 테이블이 비어있는 경우 빈 옵셔널을 반환한다.")
    void notFindOne() {
        assertThat(turnRepository.findAny()).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("전체 데이터를 삭제한다.")
    void deleteAll() {
        turnRepository.deleteAll();
        assertThat(turnRepository.findAny()).isEmpty();
    }
}
