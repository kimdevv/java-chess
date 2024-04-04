package domain.coordinate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoordinateMapper {

    public static final Map<String, Coordinate> CACHE_FROM_NAME = new HashMap<>();
    public static final Map<Coordinate, String> CACHE_FROM_COORDINATE = new HashMap<>();

    static {
        Arrays.stream(Column.values())
                .forEach(column -> Arrays.stream(Row.values()).forEach(row -> {
                    String name = column.name().toLowerCase() + row.getName();
                    CACHE_FROM_NAME.put(name, new Coordinate(row.getValue(), column.getValue()));
                    CACHE_FROM_COORDINATE.put(new Coordinate(row.getValue(), column.getValue()), name);
                }));
    }

    private CoordinateMapper() {
    }

    public static Coordinate getCoordinate(String name) {
        return CACHE_FROM_NAME.get(name);
    }

    public static String getCoordinateName(Coordinate coordinate) {
        return CACHE_FROM_COORDINATE.get(coordinate);
    }
}
