package chess.domain.chesspiece;

import java.util.Arrays;

public enum Camp {

    BLACK,
    WHITE,
    NONE;

    Camp() {
    }

    public boolean isSameCamp(Camp camp) {
        return this.equals(camp);
    }

    public boolean isBlackCamp() {
        return this.equals(BLACK);
    }

    public boolean isWhiteCamp() {
        return this.equals(WHITE);
    }

    public boolean isNoneCamp() {
        return this.equals(NONE);
    }

    public static Camp findCamp(String camp) {
        return Arrays.stream(values())
                .filter(campObj -> campObj.toString().equals(camp))
                .findFirst()
                .orElseThrow();
    }

    public Camp findEnemyCamp() {
        return Arrays.stream(values())
                .filter(camp -> !isNoneCamp() && !this.equals(camp))
                .findFirst()
                .orElseThrow();
    }
}
