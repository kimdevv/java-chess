package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;

import java.util.List;

public class End implements GameState {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";

    private final Team team;

    public End(Team team) {
        this.team = team;
    }

    public End() {
        team = Team.NONE;
    }

    @Override
    public Team findWinner() {
        return team;
    }

    @Override
    public GameState play(List<String> inputCommand) {
        String command = inputCommand.get(0);
        if (command.equals(START_COMMAND)) {
            throw new UnsupportedOperationException("종료한 게임은 다시 시작할 수 없습니다.");
        }
        if (command.equals(MOVE_COMMAND)) {
            throw new UnsupportedOperationException("종료한 게임은 진행할 수 없습니다.");
        }
        if (command.equals(END_COMMAND)) {
            throw new UnsupportedOperationException("종료한 게임은 다시 종로할 수 없습니다.");
        }
        throw new IllegalArgumentException("올바르지 않은 command입니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        throw new UnsupportedOperationException("종료한 게임의 체스판은 초기화됩니다.");
    }
}
