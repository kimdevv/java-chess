package chess.view;

import java.awt.Point;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public GameCommand getGameCommand() {
        return GameCommand.getGameCommand(scanner.next());
    }

    public Point getPosition() {
        String input = scanner.next();
        validateEmpty(input);
        return PointConverter.convert(input);
    }

    private void validateEmpty(final String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("이동 명령어의 위치 값이 필요합니다.");
        }
    }

    public boolean isPlaySavedGame() {
        System.out.println("저장된 게임이 있습니다. 이어하시겠습니까? (y or n)");
        String option = scanner.next();
        validateSaveGameOption(option);
        return Objects.equals(option, "y");
    }

    private void validateSaveGameOption(final String option) {
        if (!Objects.equals(option, "y") && !Objects.equals(option, "n")) {
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }
    }
}
