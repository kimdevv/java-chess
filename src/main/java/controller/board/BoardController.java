package controller.board;

import application.BoardService;
import dto.MovementDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import ui.InputView;
import ui.output.BoardOutputView;

public class BoardController {
    private final InputView inputView;
    private final BoardOutputView outputView;
    private final BoardService boardService;
    private final Map<BoardCommand, BiConsumer<List<String>, Integer>> commands;

    public BoardController(InputView inputView, BoardOutputView outputView, BoardService boardService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.boardService = boardService;
        this.commands = new EnumMap<>(BoardCommand.class);
    }

    private void putCommands() {
        commands.put(BoardCommand.MOVE, this::move);
        commands.put(BoardCommand.STATUS, (ignore, roomId) -> status(roomId));
        commands.put(BoardCommand.END, (ignore, roomId) -> {
        });
    }

    public void run(int roomId) {
        putCommands();
        outputView.printStartMessage();
        boardService.loadBoard(roomId);
        outputView.printBoard(boardService.getChessBoard(roomId));
        BoardCommand command = BoardCommand.NONE;
        while (command != BoardCommand.END) {
            command = playGame(roomId);
            command = verifyKingCaptured(command, roomId);
        }
    }

    private BoardCommand playGame(int roomId) {
        try {
            outputView.printCommandMessage();
            List<String> rawCommands = inputView.readCommandNameAndArgs();
            BoardCommand command = BoardCommand.findCommand(rawCommands);
            commands.get(command).accept(rawCommands, roomId);
            return command;
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return BoardCommand.NONE;
        }
    }

    private void move(List<String> rawCommands, int roomId) {
        MovementDto movementDto = MovementDto.from(rawCommands);
        boardService.move(movementDto, roomId);
        outputView.printBoard(boardService.getChessBoard(roomId));
    }

    private void status(int roomId) {
        outputView.printBoardStatus(boardService.getChessBoard(roomId));
    }

    private BoardCommand verifyKingCaptured(BoardCommand command, int roomId) {
        if (boardService.isGameOver(roomId)) {
            outputView.printKingCapturedMessage(boardService.getWinner(roomId));
            boardService.removeBoard(roomId);
            return BoardCommand.END;
        }
        return command;
    }
}
