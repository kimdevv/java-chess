package dao.mapper;

import fixture.PositionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionDaoMapperTest {

    @DisplayName("position을 db 데이터로 만든다")
    @Test
    void positionToData() {
        String data = PositionDaoMapper.positionToData(PositionFixture.a1());
        Assertions.assertThat(data).isEqualTo("a1");
    }
}
