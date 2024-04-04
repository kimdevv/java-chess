package chess.dto;

import chess.domain.Command;
import java.util.Arrays;
import java.util.List;

public class CommandDto {

    private final Command command;
    private final List<String> body;

    private CommandDto(final Command command, final List<String> body) {
        this.command = command;
        this.body = body;
    }

    public static CommandDto fromStart(final String commandDto) {
        final Command command = Command.from(commandDto);

        if (!(command.isStart() || command.isEnd() || command.isReload())) {
            throw new IllegalArgumentException("[ERROR] 시작 명령어는 start,end 혹은 reload입니다.");
        }

        return new CommandDto(command, List.of());
    }

    public static CommandDto fromPlay(final String commandDto) {
        final List<String> splitCommand = splitCommand(commandDto);
        final Command command = Command.from(splitCommand.get(0));

        if (command.isStart()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 명령어입니다.");
        }

        if (command.isMove()) {
            return new CommandDto(command, getPositions(splitCommand));
        }

        return new CommandDto(command, List.of());
    }

    private static List<String> getPositions(final List<String> commandDto) {
        if (commandDto.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 움직일 위치를 작성해주세요.");
        }

        return commandDto.subList(1, 3);
    }

    private static List<String> splitCommand(final String command) {
        return Arrays.asList(command.split(" "));
    }

    public boolean isReload() { return command.isReload(); }

    public boolean isStart() {
        return command.isStart();
    }

    public boolean isMove() {
        return command.isMove();
    }

    public boolean isEnd() {
        return command.isEnd();
    }

    public boolean isStatus() {
        return command.isStatus();
    }

    public List<String> getBody() {
        return body;
    }
}
