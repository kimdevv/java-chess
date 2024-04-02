package chess.domain.board;

import java.util.Arrays;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String file;
    private final int index;

    File(String file, int index) {
        this.file = file;
        this.index = index;
    }

    public static File getFile(String alphabet) {
        return Arrays.stream(File.values())
                .filter(value -> value.getRawFile().equals(alphabet))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("좌표의 열은 a~z 사이의 알파벳이어야 합니다."));
    }

    public static File getFile(int alphabet) {
        return Arrays.stream(File.values())
                .filter(value -> value.getIndex() == alphabet)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("좌표의 열은 a~z 사이의 알파벳이어야 합니다."));
    }

    public String getRawFile() {
        return file;
    }

    public int getIndex() {
        return index;
    }
}
