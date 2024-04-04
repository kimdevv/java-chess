package dao.mapper;

import domain.board.File;

import java.util.Arrays;

public enum FileDaoMapper {

    A(File.A, "a"),
    B(File.B, "b"),
    C(File.C, "c"),
    D(File.D, "d"),
    E(File.E, "e"),
    F(File.F, "f"),
    G(File.G, "g"),
    H(File.H, "h"),
    ;

    private final File file;
    private final String symbol;

    FileDaoMapper(File file, String symbol) {
        this.file = file;
        this.symbol = symbol;
    }

    public static File dataToFile(String input) {
        return Arrays.stream(values())
                .filter(file -> file.symbol.equals(input))
                .findFirst()
                .map(it -> it.file)
                .orElseThrow(() -> new IllegalArgumentException("a ~ h 사이의 file을 입력해주세요."));
    }
}
