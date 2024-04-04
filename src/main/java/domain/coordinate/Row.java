package domain.coordinate;

public enum Row {

    FIRST("1", 7),
    SECOND("2", 6),
    THIRD("3", 5),
    FOURTH("4", 4),
    FIFTH("5", 3),
    SIXTH("6", 2),
    SEVENTH("7", 1),
    EIGHTH("8", 0);

    private final String name;
    private final int value;

    Row(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
