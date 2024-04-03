package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    void 다른_rank와_사이에_있는_rank를_찾을_때_자신의_rank가_더_작은_경우_오름차순으로_반환한다() {
        Rank rank = Rank.FOUR;
        Rank other = Rank.EIGHT;

        List<Rank> ranks = rank.findRanksBetween(other);

        assertThat(ranks).containsExactly(Rank.FIVE, Rank.SIX, Rank.SEVEN);
    }

    @Test
    void 다른_rank와_사이에_있는_rank를_찾을_때_자신의_rank가_더_큰_경우_내림차순으로_반환한다() {
        Rank rank = Rank.EIGHT;
        Rank other = Rank.FOUR;

        List<Rank> ranks = rank.findRanksBetween(other);

        assertThat(ranks).containsExactly(Rank.SEVEN, Rank.SIX, Rank.FIVE);
    }
}
