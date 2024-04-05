package chess.domain.piece.attribute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;

public class Positions {

    private static final int FILE_COUNT = 8;

    private static final Map<Integer, Position> CACHED_POSITIONS = new HashMap<>();

    private Positions() {
    }

    public static Set<Position> of(final String... positions) {
        initializePositions();
        return Arrays.stream(positions)
                .map(Position::from)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static Set<Position> from(final File file) {
        return from(position -> position.file() == file);
    }

    public static Set<Position> from(final Rank rank) {
        return from(position -> position.rank() == rank);
    }

    public static Set<Position> from(final Predicate<Position> predicate) {
        initializePositions();
        return CACHED_POSITIONS.values()
                .stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableSet());
    }

    protected static Position get(final File file, final Rank rank) {
        initializePositions();
        return CACHED_POSITIONS.get(keyOf(file, rank));
    }

    private static void initializePositions() {
        if (!CACHED_POSITIONS.isEmpty()) {
            return;
        }
        for (final Rank rank : Rank.values()) {
            putPositions(rank);
        }
    }

    private static void putPositions(final Rank rank) {
        for (final File file : File.values()) {
            CACHED_POSITIONS.put(keyOf(file, rank), new Position(file, rank));
        }
    }

    private static int keyOf(final File file, final Rank rank) {
        return rank.toRow() * FILE_COUNT + file.toColumn();
    }
}
