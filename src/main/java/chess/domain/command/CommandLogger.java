package chess.domain.command;

import static chess.domain.command.CommandType.END;
import static chess.domain.command.CommandType.LOAD;
import static chess.domain.command.CommandType.START;

import java.util.ArrayList;
import java.util.List;

public class CommandLogger {
    private final List<Command> commandLog;

    public CommandLogger() {
        this.commandLog = new ArrayList<>();
    }

    public void addLog(Command command) {
        commandLog.add(command);
    }

    public void checkCommandValidate(Command command) {
        validateFirstCommandStartOrLoad(command.getCommandType());
        validateStartOrLoadNotFirstCommand(command.getCommandType());
    }

    private void validateFirstCommandStartOrLoad(CommandType commandType) {
        if (commandLog.isEmpty() && commandType != START && commandType != LOAD) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void validateStartOrLoadNotFirstCommand(CommandType commandType) {
        if (!commandLog.isEmpty() && (commandType == START || commandType == LOAD)) {
            throw new IllegalArgumentException("START나 LOAD는 게임 시작 시에만 선택할 수 있습니다.");
        }
    }

    public boolean isCommandEnd() {
        if (commandLog.isEmpty()) {
            return false;
        }
        return getLastCommandType() == END;
    }

    public boolean isGameStart() {
        return !commandLog.isEmpty();
    }

    private CommandType getLastCommandType() {
        if (commandLog.isEmpty()) {
            throw new IllegalArgumentException("로그가 비어있습니다.");
        }

        Command lastCommand = commandLog.get(commandLog.size() - 1);
        return lastCommand.getCommandType();
    }
}
