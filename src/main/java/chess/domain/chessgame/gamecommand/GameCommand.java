package chess.domain.chessgame.gamecommand;

import java.util.Arrays;

public enum GameCommand {

    START("start", StartState.getInstance()),
    MOVE("move", MoveState.getInstance()),
    END("end", EndState.getInstance()),
    STATUS("status", StatusState.getInstance());

    private final String command;
    private final GameCommandState state;

    GameCommand(String command, GameCommandState state) {
        this.command = command;
        this.state = state;
    }

    public static GameCommand findGameCommand(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.isAvailableCommand(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값과 일치하는 게임 명령이 없습니다."));
    }

    public GameCommandState findGameCommandState() {
        GameCommand findGameCommand = Arrays.stream(values())
                .filter(gameCommand -> gameCommand.equals(this))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return findGameCommand.state;
    }

    private boolean isAvailableCommand(String input) {
        return command.equals(input);
    }

    public boolean isNotFinishedGame() {
        return !command.equals(END.command);
    }

    public boolean isGameStarted() {
        return command.equals(START.command);
    }

    public boolean isMovedChessPiece() {
        return command.equals(MOVE.command);
    }
}
