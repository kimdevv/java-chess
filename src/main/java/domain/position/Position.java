package domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {
    private static final Map<String, Position> POSITION_CACHE;

    static {
        POSITION_CACHE = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> Map.entry(file.expression() + rank.number(), new Position(file, rank))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String position) {
        if (!POSITION_CACHE.containsKey(position)) {
            throw new IllegalArgumentException("존재하지 않는 위치입니다.");
        }
        return POSITION_CACHE.get(position);
    }

    public static Position of(File file, Rank rank) {
        return from(file.expression() + rank.number());
    }

    public List<Position> findPathTo(Position target) {
        List<File> files = file.findFilesBetween(target.file);
        List<Rank> ranks = rank.findRanksBetween(target.rank);

        if (isStraightDirectionTo(target)) {
            return generateStraightPath(files, ranks);
        }
        if (isDiagonalDirectionTo(target)) {
            return generateDiagonalPath(files, ranks);
        }
        return Collections.emptyList();
    }

    private List<Position> generateStraightPath(List<File> files, List<Rank> ranks) {
        if (files.isEmpty()) {
            return generateVerticalPath(ranks);
        }
        return generateHorizontalPath(files);
    }

    private List<Position> generateVerticalPath(List<Rank> ranks) {
        return ranks.stream()
                .map(rank -> new Position(this.file, rank))
                .toList();
    }

    private List<Position> generateHorizontalPath(List<File> files) {
        return files.stream()
                .map(file -> new Position(file, this.rank))
                .toList();
    }

    private List<Position> generateDiagonalPath(List<File> files, List<Rank> ranks) {
        return IntStream.range(0, files.size())
                .mapToObj(i -> new Position(files.get(i), ranks.get(i)))
                .toList();
    }

    public void validateDifferentPosition(Position target) {
        if (this.equals(target)) {
            throw new IllegalArgumentException("같은 칸입니다.");
        }
    }

    public boolean isStraightDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) == 0 || calculateRankGap(target) == 0;
    }

    public boolean isDiagonalDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) == calculateRankGap(target);
    }

    public boolean isKnightDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) * calculateRankGap(target) == 2;
    }

    public boolean isVerticalDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) == 0;
    }

    public int calculateDistance(Position target) {
        return Math.max(calculateRankGap(target), calculateFileGap(target));
    }

    private int calculateRankGap(Position target) {
        return Math.abs(this.rank.number() - target.rank.number());
    }

    private int calculateFileGap(Position target) {
        return Math.abs(this.file.order() - target.file.order());
    }

    public boolean isUpperRankThan(Position target) {
        return this.rank.number() > target.rank.number();
    }

    public boolean isLowerRankThan(Position target) {
        return this.rank.number() < target.rank.number();
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public Rank rank() {
        return rank;
    }

    public File file() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
