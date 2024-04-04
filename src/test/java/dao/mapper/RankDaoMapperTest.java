package dao.mapper;

import domain.board.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankDaoMapperTest {

    @DisplayName("db 데이터를 Rank로 만든다")
    @Test
    void dataToFile() {
        Rank rank = RankDaoMapper.dataToRank("1");
        Assertions.assertThat(rank).isEqualTo(Rank.ONE);
    }
}
