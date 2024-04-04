package domain.coordinate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateMapperTest {

    // 두 리스트를 CoordinateMapper 로 매핑하면, 정확히 서로를 반환해야 한다.
    List<String> nameCoordinates = List.of("a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1");
    List<Coordinate> coordinates = List.of(
            new Coordinate(7, 0),
            new Coordinate(7, 1),
            new Coordinate(7, 2),
            new Coordinate(7, 3),
            new Coordinate(7, 4),
            new Coordinate(7, 5),
            new Coordinate(7, 6),
            new Coordinate(7, 7)
    );

    @DisplayName("이름을 좌표로 매핑한다.")
    @Test
    void mapNameToCoordinate() {
        List<String> given = nameCoordinates;

        List<Coordinate> actual = given.stream()
                .map(CoordinateMapper::getCoordinate)
                .toList();
        List<Coordinate> expected = coordinates;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("좌표를 이름으로 매핑한다.")
    @Test
    void mapCoordinateToName() {
        List<Coordinate> given = coordinates;

        List<String> actual = given.stream()
                .map(CoordinateMapper::getCoordinateName)
                .toList();
        List<String> expected = nameCoordinates;

        assertThat(actual).isEqualTo(expected);
    }
}
