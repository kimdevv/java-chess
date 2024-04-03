package chess.controller.command;

import java.util.Arrays;

public enum GameCommand {
    START("start", 0),
    END("end", 0),
    MOVE("move", 2),
    STATUS("status", 0),
    ;

    private final String command;
    private final int argsCount;

    GameCommand(String command, int argsCount) {
        this.command = command;
        this.argsCount = argsCount;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.isSameCommand(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 명령어입니다."));
    }

    private boolean isSameCommand(String command) {
        return this.command.equals(command);
    }

    public boolean isSameArgsCount(int argsCount) {
        return this.argsCount == argsCount;
    }
}
