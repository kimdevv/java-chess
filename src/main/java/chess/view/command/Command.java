package chess.view.command;

public class Command {

    private final CommandType commandType;
    private final String description;

    public Command(final String command) {
        this.commandType = CommandType.from(command);
        this.description = command;
    }

    public MoveCommand toMoveCommand() {
        if (isMove()) {
            return new MoveCommand(description);
        }
        throw new IllegalStateException("이동 명령어에서만 실행할 수 있습니다.");
    }

    public boolean isStart() {
        return commandType == CommandType.START;
    }

    public boolean isEnd() {
        return commandType == CommandType.END;
    }

    public boolean isMove() {
        return commandType == CommandType.MOVE;
    }

    public boolean isStatus() {
        return commandType == CommandType.STATUS;
    }

    public boolean isLoad() {
        return commandType == CommandType.LOAD;
    }
}
