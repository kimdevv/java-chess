package db.booleanTranslator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BooleanTranslatorTest {

    @DisplayName("true에 일치하는 값을 return 한다.")
    @Test
    void findTrueCorrespondentTinyInt() {
        Assertions.assertThat(BooleanTranslator.translate(true)).isEqualTo(1);
    }

    @DisplayName("false에 일치하는 값을 return 한다.")
    @Test
    void findFalseCorrespondentTinyInt() {
        Assertions.assertThat(BooleanTranslator.translate(false)).isEqualTo(0);
    }
}
