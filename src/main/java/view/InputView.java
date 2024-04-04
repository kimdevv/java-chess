package view;

import command.Command;
import view.mapper.CommandMapper;

import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Scanner scanner = new Scanner(System.in);

    public Command readInitCommand() {
        System.out.println(String.join(LINE_SEPARATOR, "> 체스 게임을 시작합니다.",
                "> 게임 시작 : start",
                "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3",
                "> 점수 현황 : status"));
        return CommandMapper.makeCommand(scanner.nextLine());
    }

    public String readRoomName() {
        System.out.println("방 이름을 입력해주세요.");
        return scanner.nextLine();
    }

    public Command readCommand() {
        return CommandMapper.makeCommand(scanner.nextLine());
    }
}
