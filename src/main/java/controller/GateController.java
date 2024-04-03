package controller;

import controller.room.RoomController;
import ui.InputView;
import ui.output.GateOutputView;

public class GateController {
    private final InputView inputView;
    private final GateOutputView outputView;
    private final RoomController roomController;

    public GateController(InputView inputView, GateOutputView outputView, RoomController roomController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.roomController = roomController;
    }

    public void run() {
        outputView.printStartMessage();
        while (getCommand() != GateCommand.END) {
            roomController.execute();
        }
    }

    private GateCommand getCommand() {
        try {
            outputView.printCommandMessage();
            return GateCommand.findCommand(inputView.readCommandName());
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return getCommand();
        }
    }
}
