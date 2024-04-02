package chess.dao;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PositionConverter {
    private PositionConverter(){
    }

    public static Position convertToPosition(String value) {
        return new Position(value.substring(0, 1), value.substring(1));
    }

    public static String convertToString(Position value) {
        File file = value.getFile();
        Rank rank = value.getRank();

        return file.name() + rank.getIndex();
    }
}
