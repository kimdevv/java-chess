package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> receiveCommands() {
        return splitCommand(scanner.nextLine());
    }

    private static List<String> splitCommand(String input) {
        return List.of(input.split(" "));
    }

    public static String receiveRoomName() {
        System.out.print("설정하고 싶은 방 이름을 작성해주세요. (최대 20 글자) : ");
        return scanner.nextLine();
    }
}
