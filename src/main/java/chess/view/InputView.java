package chess.view;

import chess.domain.chessgame.gamecommand.GameCommand;
import chess.domain.chessgame.gamecommand.GameElements;
import chess.domain.chessgame.gamecommand.MoveElements;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int GAME_COMMAND_INDEX = 0;
    private static final int EXTRACT_MOVE_SQUARE_CONDITION = 1;
    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private InputView() {
    }

    public static GameElements requestGameCommand() {
        String input = removeBlank(scanner.nextLine());
        List<String> splitInput = List.of(input.split(" "));
        GameCommand gameCommand = createGameCommand(splitInput);
        MoveElements moveElements = createMoveElements(splitInput);
        return new GameElements(gameCommand, moveElements);
    }

    private static GameCommand createGameCommand(List<String> splitInput) {
        String inputCommand = splitInput.get(GAME_COMMAND_INDEX);
        return GameCommand.findGameCommand(inputCommand);
    }

    private static MoveElements createMoveElements(List<String> splitInput) {
        if (splitInput.size() > EXTRACT_MOVE_SQUARE_CONDITION) {
            String moveSource = extractMoveSquare(splitInput).get(MOVE_SOURCE_SQUARE_INDEX);
            String target = extractMoveSquare(splitInput).get(TARGET_SQUARE_INDEX);
            return new MoveElements(moveSource, target);
        }
        return null;
    }

    private static List<String> extractMoveSquare(List<String> input) {
        return input.subList(1, input.size());
    }

    private static String removeBlank(String input) {
        return input.trim();
    }
}
