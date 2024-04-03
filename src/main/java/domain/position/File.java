package domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private static final int DISCARDING_COUNT_FROM_FRONT = 1;

    private final String expression;
    private final int order;

    File(String expression, int order) {
        this.expression = expression;
        this.order = order;
    }

    private static File fromOrder(int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %d - 존재하지 않은 file입니다.", order)));
    }

    public List<File> findFilesBetween(File other) {
        return IntStream.range(Math.min(this.order, other.order), Math.max(this.order, other.order))
                .skip(DISCARDING_COUNT_FROM_FRONT)
                .mapToObj(File::fromOrder)
                .sorted(comparator(other))
                .toList();
    }

    private Comparator<File> comparator(File other) {
        if (this.order > other.order) {
            return (f1, f2) -> f2.order - f1.order;
        }
        return Comparator.comparingInt(f -> f.order);
    }

    public String expression() {
        return expression;
    }

    public int order() {
        return order;
    }
}
