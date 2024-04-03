package domain;

public enum Team {
    WHITE, BLACK;

    public Team turn() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public static Team from(final String name) {
        for (final Team value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 진영입니다.");
    }
}
