package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    _1("1", 1),
    _2("2", 2),
    _3("3", 3),
    _4("4", 4),
    _5("5", 5),
    _6("6", 6),
    _7("7", 7),
    _8("8", 8);

    private final String rank;
    private final int index;

    Rank(String rank, int index) {
        this.rank = rank;
        this.index = index;
    }

    public static Rank getRank(String number) {
        return Arrays.stream(Rank.values())
                .filter(value -> value.getRawRank().equals(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("좌표의 행은 1~8 사이의 숫자여야 합니다."));
    }

    public static Rank getRank(int number) {
        return Arrays.stream(Rank.values())
                .filter(value -> value.getIndex() == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("좌표의 행은 1~8 사이의 숫자여야 합니다."));
    }

    public String getRawRank() {
        return rank;
    }

    public int getIndex() {
        return index;
    }
}
