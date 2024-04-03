package controller;

public enum Menu {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String command;

    Menu(final String command) {
        this.command = command;
    }

    public static Menu fromInput(final String input) {
        for (final var value : Menu.values()) {
            if (value.command.equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException("허용하지 않은 커멘드 입니다 start | end | move {} {} 로 입력해주세요.");
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isEnd() {
        return this == END;
    }
}
