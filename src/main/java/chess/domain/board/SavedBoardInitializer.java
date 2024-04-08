package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class SavedBoardInitializer implements BoardInitializer {

    private final Map<Position, Piece> savedBoard;

    public SavedBoardInitializer(final Map<Position, Piece> savedBoard) {
        this.savedBoard = savedBoard;
    }

    @Override
    public Map<Position, Piece> initialize() {
        return savedBoard;
    }
}
