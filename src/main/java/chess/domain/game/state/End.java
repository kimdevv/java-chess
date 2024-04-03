package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.point.Point;

public class End implements State {
    private static final String END_GAME_EXCEPTION_MESSAGE = "이미 게임이 종료되었습니다.";
    private final Team team;

    public End(final Team team) {
        this.team = team;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(END_GAME_EXCEPTION_MESSAGE);
    }

    @Override
    public State finish() {
        throw new UnsupportedOperationException(END_GAME_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State move(final Board board, final Point departure, final Point destination) {
        throw new UnsupportedOperationException(END_GAME_EXCEPTION_MESSAGE);
    }

    @Override
    public double calculateScore(final Board board, final Team team) {
        throw new UnsupportedOperationException(END_GAME_EXCEPTION_MESSAGE);
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
