package chess.domain.scorerule;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PawnScoreRule extends ScoreRule {
    public PawnScoreRule() {
        super(1);
    }

    @Override
    public double findScore(final List<Position> positionPerPieces) {
        double baseScore = positionPerPieces.size() * score;

        int sameFilePositionCount = getSameFilePositionCount(positionPerPieces);
        baseScore -= sameFilePositionCount * (score / 2);
        return baseScore;
    }

    private int getSameFilePositionCount(List<Position> positionPerPieces) {
        int count = 0;
        Map<File, List<Position>> sameFilePositions = getSameFilePositions(positionPerPieces);
        for (Entry<File, List<Position>> entry : sameFilePositions.entrySet()) {
            count += getSameFileCount(entry);
        }

        return count;
    }

    private Map<File, List<Position>> getSameFilePositions(final List<Position> positionPerPieces) {
        return positionPerPieces.stream()
                .collect(groupingBy(Position::file, toList()));
    }

    private int getSameFileCount(final Entry<File, List<Position>> entry) {
        int size = entry.getValue().size();
        if (size > 1) {
            return size;
        }
        return 0;
    }
}
