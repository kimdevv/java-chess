package view.util;

import java.util.Arrays;

public enum Command {

    START("start"),
    LOAD("load"),
    MOVE("move"),
    STATUS("status"),
    SAVE("save"),
    END("end");

    private final String identifier;

    Command(String identifier) {
        this.identifier = identifier;
    }

    private static Command from(String identifier) {
        return Arrays.stream(values())
                .filter(command -> command.identifier.equals(identifier))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당되는 식별자가 존재하지 않습니다."));
    }

    public static Command getInitialCommand(String identifier) {
        Command command = from(identifier);
        if (command != START && command != LOAD) {
            throw new IllegalArgumentException("게임을 먼저 시작하거나 불러와야 합니다.");
        }
        return command;
    }

    public static Command getPlayingCommand(String identifier) {
        Command command = from(identifier);
        if (command == START || command == LOAD) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
        return command;
    }
}
