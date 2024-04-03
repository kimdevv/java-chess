package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.point.Point;

public class Ready implements State {
    public static final String GAME_READY_EXCEPTION_MESSAGE = "아직 게임이 시작하지 않아 실행할 수 없습니다.";
    private final Team team;

    public Ready(final Team team) {
        this.team = team;
    }

    @Override
    public State start() {
        return new Running(team);
    }

    @Override
    public State finish() {
        return new End(team.opposite());
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State move(final Board board, final Point departure, final Point destination) {
        throw new UnsupportedOperationException(GAME_READY_EXCEPTION_MESSAGE);
    }

    @Override
    public double calculateScore(final Board board, final Team team) {
        throw new UnsupportedOperationException(GAME_READY_EXCEPTION_MESSAGE);
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
