package chess.domain.chessboard;

import java.util.Arrays;

public enum Lettering {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    Lettering(int value) {
        this.value = value;
    }

    public static Lettering findLettering(int value) {
        return Arrays.stream(Lettering.values())
                .filter(lettering -> lettering.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 레터링 값을 찾을 수 없습니다."));
    }

    public Lettering findNextLettering() {
        if (this == Lettering.H) {
            throw new IllegalArgumentException("[ERROR] 현재 맨 오른쪽입니다. 오른쪽 레터링이 존재하지 않습니다.");
        }
        int findLetteringValue = value + 1;
        return findLettering(findLetteringValue);
    }

    public Lettering findPreviousLettering() {
        if (this == Lettering.A) {
            throw new IllegalArgumentException("[ERROR] 현재 맨 왼쪽입니다. 왼쪽 레터링이 존재하지 않습니다.");
        }
        int findLetteringValue = value - 1;
        return findLettering(findLetteringValue);
    }

    public int getValue() {
        return value;
    }
}
