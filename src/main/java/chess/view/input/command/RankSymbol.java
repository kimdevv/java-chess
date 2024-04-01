package chess.view.input.command;

import chess.domain.position.Rank;
import java.util.Arrays;

public enum RankSymbol {
    ONE(Rank.ONE, "1"),
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),
    ;

    private final Rank rank;
    private final String symbol;

    RankSymbol(final Rank rank, final String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static RankSymbol getRankSymbol(final String symbol) {
        return Arrays.stream(RankSymbol.values())
                .filter(rankSymbol -> rankSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("랭크는 1 ~ 8 까지 입력 가능합니다."));
    }

    public Rank getRank() {
        return rank;
    }
}
