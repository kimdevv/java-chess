package chess.domain.point;

import chess.domain.piece.Direction;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Point {

    private static final int SlOPE_TWO = 2;
    private static final Map<String, Point> POOL;

    private final File file;
    private final Rank rank;

    static {
        POOL = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Point(file, rank)))
                .collect(Collectors.toMap(it -> toKey(it.file, it.rank), Function.identity()));
    }

    private Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Point of(File file, Rank rank) {
        return POOL.computeIfAbsent(toKey(file, rank), ignore -> {
            throw new IllegalArgumentException("존재하지 않는 Point 입니다.");
        });
    }

    public static Point of(String pointKey) {
        String key = pointKey.toUpperCase();
        return POOL.computeIfAbsent(key, ignore -> {
            throw new IllegalArgumentException("존재하지 않는 Point 입니다.");
        });
    }

    public boolean isSlopeOneDiagonal(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        if (this == point || rankDistance == 0) {
            return false;
        }

        return isSlopeOne(fileDistance, rankDistance);
    }

    private boolean isSlopeOne(int fileDistance, int rankDistance) {
        return Math.abs(fileDistance / rankDistance) == 1;
    }

    public boolean isStraight(Point point) {
        if (this == point) {
            return false;
        }
        return this.file == point.file || this.rank == point.rank;
    }

    public boolean isAround(Point point) {
        if (this == point) {
            return false;
        }
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        return Math.abs(fileDistance) < 2 && Math.abs(rankDistance) < 2;
    }

    public int multiplyAxis(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        return fileDistance * rankDistance;
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public Point move(Direction direction) {
        File addedFile = file.add(direction.file());
        Rank addedRank = rank.add(direction.rank());

        return Point.of(addedFile, addedRank);
    }

    public boolean addable(Direction direction) {
        return file.addable(direction.file()) && rank.addable(direction.rank());
    }

    public Direction findUnitDirection(Point point) {
        int fileDistance = point.file.distance(this.file);
        int rankDistance = point.rank.distance(this.rank);
        if (Math.abs(multiplyAxis(point)) == SlOPE_TWO) {
            return Direction.of(fileDistance, rankDistance);
        }
        return Direction.of(unitDistance(fileDistance), unitDistance(rankDistance));
    }

    private int unitDistance(int distance) {
        if (distance == 0) {
            return 0;
        }
        return distance < 0 ? -1 : 1;
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.getRank();
    }
}
