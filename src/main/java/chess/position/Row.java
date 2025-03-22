package chess.position;

public enum Row {

    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

    public Row move(final int step) {
        if (canMove(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean canMove(final int step) {
        return ordinal() + step >= 0 && ordinal() + step < values().length;
    }

    public int calculateDifference(final Row otherRow) {
        return ordinal() - otherRow.ordinal();
    }
}
