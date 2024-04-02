package chess.domain.command;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CommandType {
    START("start"),
    LOAD("load"),
    MOVE("move"),
    STATUS("status"),
    SAVE("save"),
    END("end");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String get() {
        return commandType;
    }

    public static CommandType getCommand(String command) {
        return Arrays.stream(CommandType.values())
                .filter(value -> value.get().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "커맨드 타입은 " + getCommandTypes() + " 중 하나여야 합니다."));
    }

    private static String getCommandTypes() {
        return Arrays.stream(CommandType.values())
                .map(CommandType::get)
                .collect(Collectors.joining(", "));
    }
}
