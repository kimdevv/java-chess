package chess.dao.db.symbol;

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

    public static FileSymbol getFileSymbolByFile(File file) {
        return Arrays.stream(FileSymbol.values()).filter(fileSymbol -> fileSymbol.file == file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("저장할 수 없는 파일입니다."));
    }

    public static FileSymbol getFileSymbolBySymbol(String symbol) {
        return Arrays.stream(FileSymbol.values()).filter(fileSymbol -> fileSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불러 올 수 없는 파입니다."));
    }

    public File getFile() {
        return file;
    }

    public String getSymbol() {
        return symbol;
    }
}
