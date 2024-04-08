package chess.view;

import java.util.Arrays;

public enum GameCommand {
    START("start", "게임 시작 : start"),
    END("end", "게임 종료 : end"),
    MOVE("move", "게임 이동 : move source위치 target위치 - 예. move b2 b3"),
    STATUS("status", "현재 스코어 확인 : status");

    private final String consoleCommand;
    private final String helperMassage;

    GameCommand(final String consoleCommand, final String helperMassage) {
        this.consoleCommand = consoleCommand;
        this.helperMassage = helperMassage;
    }

    public static GameCommand getGameCommand(final String input) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.consoleCommand.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어 입니다."));
    }

    public String getHelperMessage() {
        return helperMassage;
    }

    public boolean isOnGoingGameCommand() {
        return this != GameCommand.START && this != GameCommand.END;
    }

    public boolean isStartGameCommand() {
        return this == GameCommand.START;
    }

    public boolean isNotStartCommand() {
        return this != GameCommand.START;
    }

    public boolean isMoveCommand() {
        return this == GameCommand.MOVE;
    }

    public boolean isViewStatusCommand() {
        return this == GameCommand.STATUS;
    }

    public boolean isEndCommand() {
        return this == GameCommand.END;
    }
}
