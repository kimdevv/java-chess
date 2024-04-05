package chess.view.mapper;

import chess.controller.command.Command;
import chess.controller.command.End;
import chess.controller.command.Move;
import chess.controller.command.Resume;
import chess.controller.command.Save;
import chess.controller.command.Start;
import chess.controller.command.Status;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.function.Function;

public enum CommandMapper {

    START(input -> input.equals("start"), input -> new Start()),
    RESUME(input -> input.equals("resume"), input -> new Resume()),
    END(input -> input.equals("end"), input -> new End()),
    MOVE(CommandMapper::isMove, CommandMapper::toMove),
    STATUS(input -> input.equals("status"), input -> new Status()),
    SAVE(input -> input.equals("save"), input -> new Save()),
    ;

    private final Function<String, Boolean> isCommand;
    private final Function<String, Command> toCommand;

    CommandMapper(Function<String, Boolean> isCommand, Function<String, Command> toCommand) {
        this.isCommand = isCommand;
        this.toCommand = toCommand;
    }

    private static boolean isMove(String input) {
        String[] split = input.split(" ");
        return split.length == 3 && split[0].equals("move");
    }

    private static Command toMove(String input) {
        String[] split = input.split(" ");
        Position source = toPosition(split[1]);
        Position target = toPosition(split[2]);
        return new Move(source, target);
    }

    private static Position toPosition(String string) {
        String file = string.substring(0, 1);
        String rank = string.substring(1);
        return new Position(FileMapper.from(file), RankMapper.from(rank));
    }

    public static Command toCommand(String input) {
        return Arrays.stream(values())
                .filter(it -> it.isCommand.apply(input))
                .findAny()
                .map(it -> it.toCommand.apply(input))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
