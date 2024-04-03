package chess.controller.command;

import java.util.List;

public record CommandCondition(GameCommand gameCommand, List<String> args) {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    public static CommandCondition of(List<String> inputCommand) {
        GameCommand gameCommand = GameCommand.from(inputCommand.get(COMMAND_INDEX));
        List<String> args = inputCommand.subList(COMMAND_INDEX + 1, inputCommand.size());

        validateIsSameArgsCount(gameCommand, args);

        return new CommandCondition(gameCommand, args);
    }

    private static void validateIsSameArgsCount(GameCommand gameCommand, List<String> args) {
        if (!gameCommand.isSameArgsCount(args.size())) {
            throw new IllegalArgumentException("명령어의 인자 개수가 올바르지 않습니다.");
        }
    }

    public String getSource() {
        validateIsMoveCommand();

        return args.get(SOURCE_INDEX);
    }

    public String getTarget() {
        validateIsMoveCommand();

        return args.get(TARGET_INDEX);
    }

    private void validateIsMoveCommand() {
        if (gameCommand != GameCommand.MOVE) {
            throw new IllegalArgumentException("MOVE 명령어가 아닙니다.");
        }
    }
}
