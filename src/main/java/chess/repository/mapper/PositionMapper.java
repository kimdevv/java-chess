package chess.repository.mapper;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PositionMapper {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public static Position mapToPosition(String value) {
        File file = File.from(value.charAt(FILE_INDEX) - 'a');
        Rank rank = Rank.from(8 - (value.charAt(RANK_INDEX) - '0'));
        return new Position(file, rank);
    }

    public static String mapPositionToValue(Position position) {
        File file = position.getFile();
        Rank rank = position.getRank();
        return file.name().toLowerCase() + rank.getRankNumber();
    }
}
