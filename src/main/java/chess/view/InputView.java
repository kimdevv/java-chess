package chess.view;

import chess.controller.command.Command;
import chess.view.mapper.CommandMapper;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        return CommandMapper.toCommand(scanner.nextLine());
    }
}
