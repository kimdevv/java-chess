package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Position {

    private static final List<Position> CACHED_POSITIONS;
    static {
        CACHED_POSITIONS = Arrays.stream(File.values())
                .flatMap(file -> makePosition(file)).toList();
    }
    
    File file;
    Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static Stream<Position> makePosition(final File file) {
        return Arrays.stream(Rank.values()).map(rank -> new Position(file, rank));
    }

    public static Position of(final File file, final Rank rank) {
        return CACHED_POSITIONS.stream()
                .filter(position -> isSameFile(position, file) && isSameRank(position, rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 파일과 랭크로 위치를 생성할 수 없습니다."));
    }

    private static boolean isSameFile(final Position position, final File file) {
        return position.file == file;
    }

    private static boolean isSameRank(final Position position, final Rank rank) {
        return position.rank == rank;
    }

    public boolean isMinimumFile() {
        return file.isMinimum();
    }

    public boolean isMaximumFile() {
        return file.isMaximum();
    }

    public boolean isMinimumRank() {
        return rank.isMinimum();
    }

    public boolean isMaximumRank() {
        return rank.isMaximum();
    }

    public boolean isNextPositionInRange(final Vector vector) {
        var fileVector = vector.getFileVector();
        var rankVector = vector.getRankVector();
        return isFileMoveNext(fileVector) && isRankMoveNext(rankVector);
    }

    private boolean isFileMoveNext(final int rankVector) {
        return file.canMoveNext(rankVector);
    }

    private boolean isRankMoveNext(final int fileVector) {
        return rank.canMoveNext(fileVector);
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
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
