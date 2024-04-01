package chess.dao.db.symbol;

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

    RankSymbol(Rank rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static RankSymbol getRankSymbolByRank(Rank rank) {
        return Arrays.stream(RankSymbol.values()).filter(rankSymbol -> rankSymbol.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("저장할 수 없는 랭크입니다."));
    }

    public static RankSymbol getRankSymbolBySymbol(String symbol) {
        return Arrays.stream(RankSymbol.values()).filter(rankSymbol -> rankSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불러 올 수 없는 랭크입니다."));
    }

    public Rank getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }
}
