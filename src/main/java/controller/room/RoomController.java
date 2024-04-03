package controller.room;

import application.RoomService;
import controller.board.BoardController;
import domain.room.Room;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import ui.InputView;
import ui.output.RoomOutputView;

public class RoomController {
    private static final int ARG_INDEX = 1;

    private final InputView inputView;
    private final RoomOutputView outputView;
    private final RoomService roomService;
    private final BoardController boardController;
    private final Map<RoomCommand, Consumer<List<String>>> commands;

    public RoomController(InputView inputView, RoomOutputView outputView, RoomService roomService,
                          BoardController boardController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.roomService = roomService;
        this.boardController = boardController;
        this.commands = new EnumMap<>(RoomCommand.class);
    }

    private void putCommands() {
        commands.put(RoomCommand.CREATE, this::create);
        commands.put(RoomCommand.DELETE, this::delete);
        commands.put(RoomCommand.SHOW, (ignore) -> showRooms());
        commands.put(RoomCommand.ENTER, this::enter);
        commands.put(RoomCommand.END, (ignore) -> {
        });
    }

    public void execute() {
        putCommands();
        RoomCommand command = RoomCommand.NONE;
        while (command != RoomCommand.END) {
            command = playGame();
        }
    }

    private RoomCommand playGame() {
        try {
            outputView.printCommandMessage();
            List<String> rawCommands = inputView.readCommandNameAndArgs();
            RoomCommand command = RoomCommand.findCommand(rawCommands);
            commands.get(command).accept(rawCommands);
            return command;
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return RoomCommand.NONE;
        }
    }

    private void create(List<String> rawCommands) {
        String name = String.join(" ", rawCommands.subList(ARG_INDEX, rawCommands.size()));
        roomService.save(name);
        outputView.printCreateRoomMessage(name);
    }

    private void delete(List<String> rawCommands) {
        int roomId = Integer.parseInt(rawCommands.get(ARG_INDEX));
        roomService.deleteById(roomId);
        outputView.printDeleteRoomMessage(roomId);
    }

    private void showRooms() {
        List<Room> rooms = roomService.findAll();
        outputView.printRooms(rooms);
    }

    private void enter(List<String> rawCommands) {
        int roomId = Integer.parseInt(rawCommands.get(ARG_INDEX));
        roomService.validateRoomId(roomId);
        boardController.run(roomId);
    }
}
