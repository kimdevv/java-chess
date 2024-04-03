package controller.room;

import controller.exception.CommandNotFoundException;
import java.util.Arrays;
import java.util.List;

public enum RoomCommand {
    CREATE(21),
    DELETE(1),
    ENTER(1),
    SHOW(0),
    END(0),
    NONE(0);

    private static final int COMMAND_NAME_INDEX = 0;

    private final int argsCount;

    RoomCommand(int argsCount) {
        this.argsCount = argsCount;
    }

    public static RoomCommand findCommand(List<String> rawCommands) {
        String commandName = rawCommands.get(COMMAND_NAME_INDEX);
        RoomCommand roomCommand = Arrays.stream(values())
                .filter(RoomCommand::isNotNone)
                .filter(command -> command.name().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(commandName));
        roomCommand.validateArgsCount(rawCommands);
        return roomCommand;
    }

    private boolean isNotNone() {
        return this != NONE;
    }

    private void validateArgsCount(List<String> rawCommands) {
        List<String> args = rawCommands.subList(COMMAND_NAME_INDEX + 1, rawCommands.size());
        if (this.argsCount < args.size()) {
            throw new IllegalArgumentException("명령어의 길이가 올바르지 않습니다.");
        }
    }
}
