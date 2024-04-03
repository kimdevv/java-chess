package controller.exception;

public class CommandNotFoundException extends IllegalArgumentException {
    public CommandNotFoundException(String invalidCommandName) {
        super(String.format("rejected value: %s - 존재하지 않는 명령어입니다.", invalidCommandName));
    }
}
