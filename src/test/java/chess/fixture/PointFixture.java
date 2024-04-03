package chess.fixture;

import chess.domain.point.Point;

public class PointFixture {

    public static Point 포인트(String input) {
        char file = Character.toLowerCase(input.charAt(0));
        int rank = Character.getNumericValue(input.charAt(1));

        return Point.of(file, rank);
    }
}
