package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class BlackState extends MoveState {
    private static final BlackState INSTANCE = new BlackState();

    private BlackState() {
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, Color.BLACK);

        return WhiteState.getInstance();
    }

    @Override
    public GameState status() {
        return this;
    }

    public static BlackState getInstance() {
        return BlackState.INSTANCE;
    }
}
