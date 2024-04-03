package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Position;

public class MoveState extends RunningState {
    private final Color color;

    public MoveState(Color color) {
        this.color = color;
    }

    @Override
    public GameState move(Board board, Position source, Position target) {
        board.move(source, target, color);

        if (board.isKingDead(color.getOpposite())) {
            return new EndState();
        }

        return new MoveState(color.getOpposite());
    }

    @Override
    public Color getCurrentColor() {
        return color;
    }
}
