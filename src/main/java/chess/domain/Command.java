package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command getStartCommand(String command) {
        Command inputCommand = findCommand(command);
        if (inputCommand != START) {
            throw new IllegalArgumentException("첫 명령어는 start만 입력 가능합니다.");
        }
        return inputCommand;
    }

    public static Command getProcessCommand(String command) {
        Command inputCommand = findCommand(command);

        if (inputCommand != MOVE && inputCommand != END) {
            throw new IllegalArgumentException("게임 중에는 move 또는 end만 입력 가능합니다.");
        }

        return inputCommand;
    }

    public static Command getClosingCommand(String command) {
        Command inputCommand = findCommand(command);

        if (inputCommand != STATUS) {
            throw new IllegalArgumentException("종료 이후에는 status만 입력 가능합니다.");
        }

        return inputCommand;
    }

    private static Command findCommand(String command) {
        Command inputCommand = Arrays.stream(values())
                .filter(value -> value.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
        return inputCommand;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
