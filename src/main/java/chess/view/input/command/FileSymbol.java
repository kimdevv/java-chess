package chess.view.input.command;

import chess.domain.position.File;
import java.util.Arrays;

public enum FileSymbol {
    A(File.A, "a"),
    B(File.B, "b"),
    C(File.C, "c"),
    D(File.D, "d"),
    E(File.E, "e"),
    F(File.F, "f"),
    G(File.G, "g"),
    H(File.H, "h");

    private final File file;
    private final String symbol;

    FileSymbol(final File file, final String symbol) {
        this.file = file;
        this.symbol = symbol;
    }

    public static FileSymbol getFileSymbol(final String symbol) {
        return Arrays.stream(FileSymbol.values()).filter(fileSymbol -> fileSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일은 알파벳 소문자 a ~ h 까지 변환 가능합니다."));
    }

    public File getFile() {
        return file;
    }
}

