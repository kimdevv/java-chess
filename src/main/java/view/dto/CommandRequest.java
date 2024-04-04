package view.dto;

import view.util.Command;

public class CommandRequest {

    private final Command command;
    private final String startCoordinate;
    private final String destinationCoordinate;

    public CommandRequest(Command command, String startCoordinate, String destinationCoordinate) {
        this.command = command;
        this.startCoordinate = startCoordinate;
        this.destinationCoordinate = destinationCoordinate;
    }

    public CommandRequest(Command command) {
        this(command, null, null);
    }

    public Command getCommand() {
        return command;
    }

    public String getStartCoordinate() {
        return startCoordinate;
    }

    public String getDestinationCoordinate() {
        return destinationCoordinate;
    }
}
