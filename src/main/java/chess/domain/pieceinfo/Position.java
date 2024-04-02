package chess.domain.pieceinfo;

import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private static final int FILE_POSITION = 0;
    private static final int RANK_POSITION = 1;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String position) {
        validatePositionForm(position);

        File file = File.getFile(String.valueOf(position.charAt(FILE_POSITION)));
        Rank rank = Rank.getRank(String.valueOf(position.charAt(RANK_POSITION)));

        return new Position(file, rank);
    }

    public static void validatePositionForm(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("좌표는 두 글자 형식이어야 합니다.");
        }
    }

    public PositionDifference calculateDifference(Position otherPosition) {
        int xDifference = this.file.getIndex() - otherPosition.file.getIndex();
        int yDifference = this.rank.getIndex() - otherPosition.rank.getIndex();

        return new PositionDifference(xDifference, yDifference);
    }

    public List<Position> getInternalPositions(Position otherPosition) {
        int deltaX = otherPosition.file.getIndex() - this.file.getIndex();
        int deltaY = otherPosition.rank.getIndex() - this.rank.getIndex();
        int max = Math.max(Math.abs(deltaX), Math.abs(deltaY));

        return findInternalPositions(max, deltaX, deltaY);
    }

    public boolean isCorrectFile(int fileIndex) {
        return file.getIndex() == fileIndex;
    }

    private List<Position> findInternalPositions(int max, int deltaX, int deltaY) {
        List<Position> internalPositions = new ArrayList<>();
        for (int step = 1; step < max; step++) {
            if ((deltaX * step) % max != 0 || (deltaY * step) % max != 0) {
                continue;
            }
            int changedX = this.file.getIndex() + (deltaX / max) * step;
            int changedY = this.rank.getIndex() + (deltaY / max) * step;
            internalPositions.add(new Position(File.getFile(changedX), Rank.getRank(changedY)));
        }
        return internalPositions;
    }

    public int getFileIndex() {
        return file.getIndex();
    }

    public int getRankIndex() {
        return rank.getIndex();
    }

    public String getRawFile() {
        return file.getRawFile();
    }

    public String getRawRank() {
        return rank.getRawRank();
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
