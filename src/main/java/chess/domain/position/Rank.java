package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public int gap(Rank other) {
        return Math.abs(difference(other));
    }

    public int difference(Rank other) {
        return order - other.order;
    }

    public List<Rank> findBetween(Rank other) {
        List<Rank> ranks = Arrays.stream(values())
                .filter(rank -> isBetween(rank, this.order, other.order))
                .collect(Collectors.toList());

        if (this.order < other.order) {
            Collections.reverse(ranks);
        }
        return ranks;
    }

    private boolean isBetween(Rank rank, int thisOrder, int otherOrder) {
        return (rank.order > thisOrder && rank.order < otherOrder) ||
                (rank.order > otherOrder && rank.order < thisOrder);
    }
}
