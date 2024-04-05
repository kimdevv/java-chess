package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private final int order;

    File(int order) {
        this.order = order;
    }

    public static int length() {
        return values().length;
    }

    public int gap(File other) {
        return Math.abs(order - other.order);
    }

    public List<File> findBetween(File other) {
        List<File> files = Arrays.stream(values())
                .filter(file -> isBetween(file, this.order, other.order))
                .collect(Collectors.toList());

        if (this.order > other.order) {
            Collections.reverse(files);
        }
        return files;
    }

    private boolean isBetween(File file, int thisOrder, int otherOrder) {
        return (file.order > thisOrder && file.order < otherOrder) ||
                (file.order > otherOrder && file.order < thisOrder);
    }
}
