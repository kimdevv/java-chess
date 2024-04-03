package controller;

import controller.exception.CommandNotFoundException;
import java.util.Arrays;

public enum GateCommand {
    ROOM,
    END;

    public static GateCommand findCommand(String commandName) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(commandName));
    }
}
