package chess.domain.position;

public enum Rank {
    
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    public Rank move(final int step) {
        if (canMoveNext(step)) {
            return Rank.values()[ordinal() + step];
        }

        throw new IllegalArgumentException("해당 랭크 위치로 이동할 수 없습니다.");
    }

    public boolean canMoveNext(final int step) {
        int next = ordinal() + step;
        return next < File.values().length && next >= 0;
    }

    public boolean isMinimum() {
        return this == Rank.ONE;
    }

    public boolean isMaximum() {
        return this == Rank.EIGHT;
    }
}
