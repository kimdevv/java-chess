package chess.command;

public final class Status extends Command {

    public Status(String input) {
        super(input);
    }

    @Override
    public Command turnState(String input) {
        return new End(input);
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
