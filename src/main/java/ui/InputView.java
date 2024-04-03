package ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommandName() {
        String input = scanner.nextLine().trim();
        validateInput(input);
        System.out.println();
        return input;
    }

    public List<String> readCommandNameAndArgs() {
        String input = scanner.nextLine();
        validateInput(input);
        System.out.println();
        return Arrays.stream(input.split(" ", -1))
                .map(String::trim)
                .toList();
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }
}
