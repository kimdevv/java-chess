package view;

import java.util.Scanner;
import view.dto.CommandRequest;
import view.util.Command;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command receiveInitialCommand() {
        return Command.getInitialCommand(scanner.nextLine());
    }

    public CommandRequest receivePlayingCommand() {
        Command command = Command.getPlayingCommand(scanner.next());
        if (command == Command.MOVE) {
            return new CommandRequest(command, scanner.next(), scanner.next());
        }
        return new CommandRequest(command);
    }
}
