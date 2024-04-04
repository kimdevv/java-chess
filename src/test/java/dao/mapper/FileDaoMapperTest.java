package dao.mapper;

import domain.board.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileDaoMapperTest {

    @DisplayName("db 데이터를 File로 만든다")
    @Test
    void dataToFile() {
        File file = FileDaoMapper.dataToFile("a");
        Assertions.assertThat(file).isEqualTo(File.A);
    }
}
