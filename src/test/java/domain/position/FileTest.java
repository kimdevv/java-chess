package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FileTest {
    @Test
    void 다른_file과_사이에_있는_file을_찾을_때_자신의_file이_더_작은_경우_오름차순으로_반환한다() {
        File file = File.D;
        File other = File.H;

        assertThat(file.findFilesBetween(other)).containsExactly(File.E, File.F, File.G);
    }

    @Test
    void 다른_file과_사이에_있는_file을_찾을_때_자신의_file이_더_큰_경우_내림차순으로_반환한다() {
        File file = File.H;
        File other = File.D;

        assertThat(file.findFilesBetween(other)).containsExactly(File.G, File.F, File.E);
    }
}
