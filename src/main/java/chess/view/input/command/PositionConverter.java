package chess.view.input.command;

import chess.domain.position.Position;
import java.util.List;

public class PositionConverter {

    public static MovePath convert(final List<String> positions) {
        String from = positions.get(0);
        String to = positions.get(1);
        return new MovePath(convertPosition(from), convertPosition(to));
    }

    private static Position convertPosition(final String position) {
        String file = position.substring(0, 1);
        String rank = position.substring(1, 2);
        FileSymbol fileSymbol = FileSymbol.getFileSymbol(file);
        RankSymbol rankSymbol = RankSymbol.getRankSymbol(rank);
        return Position.of(fileSymbol.getFile(), rankSymbol.getRank());
    }
}
