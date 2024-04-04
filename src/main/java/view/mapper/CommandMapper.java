package view.mapper;

import command.*;
import domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandMapper {

    START("start", input -> new Start()),
    MOVE("move", CommandMapper::makeMove),
    STATUS("status", input -> new Status()),
    END("end", input -> new End()),
    ;

    private final String command;
    private final Function<String, Command> function;

    CommandMapper(String command, Function<String, Command> function) {
        this.command = command;
        this.function = function;
    }

    public static Command makeCommand(String input) {
        return Arrays.stream(values())
                .filter(commandMapper -> commandMapper.isMatched(input))
                .map(commandMapper -> commandMapper.function.apply(input))
                .findFirst()
                .orElseThrow();
    }

    private boolean isMatched(String input) {
        return input.startsWith(command);
    }

    private static Command makeMove(String input) {
        List<String> moveCommand = List.of(input.split(" "));
        String currentInput = moveCommand.get(1);
        String targetInput = moveCommand.get(2);

        Position current = makePosition(currentInput);
        Position target = makePosition(targetInput);
        return new Move(current, target);
    }

    private static Position makePosition(String Input) {
        String file = Input.substring(0, 1);
        String rank = Input.substring(1, 2);
        return Position.valueOf(FileMapper.from(file), RankMapper.from(rank));
    }
}
