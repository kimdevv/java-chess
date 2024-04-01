package chess.domain.position;

public enum File {
    
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public File move(final int step) {
        if (canMoveNext(step)) {
            return File.values()[ordinal() + step];
        }

        throw new IllegalArgumentException("해당 파일 위치로 이동할 수 없습니다.");
    }

    public boolean canMoveNext(final int step) {
        int next = ordinal() + step;
        return next < File.values().length && next >= 0;
    }

    public boolean isMinimum() {
        return this == File.A;
    }

    public boolean isMaximum() {
        return this == File.H;
    }
}
