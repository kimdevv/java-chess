package domain.board;

import domain.piece.Piece;
import domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Path {

    private final List<Position> path;
    private final Position target;

    private Path(List<Position> path, Position target) {
        this.path = path;
        this.target = target;
    }

    public static Path createOf(Position current, Position target) {
        List<Position> path = makePath(current, target);
        return new Path(path, target);
    }

    private static List<Position> makePath(Position current, Position target) {
        if (current.isDiagonal(target)) {
            return makeDiagonalPath(current, target);
        }
        if (current.isSameRank(target)) {
            return makeHorizontalPath(current, target);
        }
        if (current.isSameFile(target)) {
            return makeVerticalPath(current, target);
        }
        return new ArrayList<>();
    }

    private static List<Position> makeDiagonalPath(Position current, Position target) {
        List<File> files = current.findBetweenFile(target);
        List<Rank> ranks = current.findBetweenRank(target);

        return IntStream.range(0, files.size())
                .mapToObj(i -> Position.valueOf(files.get(i), ranks.get(i)))
                .toList();
    }

    private static List<Position> makeHorizontalPath(Position current, Position target) {
        List<File> files = current.findBetweenFile(target);

        return files.stream()
                .map(current::createWithSameRank)
                .toList();
    }

    private static List<Position> makeVerticalPath(Position current, Position target) {
        List<Rank> files = current.findBetweenRank(target);

        return files.stream()
                .map(current::createWithSameFile)
                .toList();
    }

    public Map<Position, Piece> findPieces(Map<Position, Piece> board) {
        Map<Position, Piece> pieces = board.entrySet().stream()
                .filter(entry -> path.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (board.containsKey(target)) {
            pieces.put(target, board.get(target));
        }

        return pieces;
    }
}
