package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class WhiteState extends MoveState {
    private static final WhiteState INSTANCE = new WhiteState();

    private WhiteState() {
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.WHITE);

        return BlackState.getInstance();
    }

    @Override
    public GameState status() {
        return this;
    }

    public static WhiteState getInstance() {
        return WhiteState.INSTANCE;
    }
}
