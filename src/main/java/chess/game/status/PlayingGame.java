package chess.game.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.result.GameResult;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.input.command.ClientCommand;
import chess.view.input.command.GameCommand;
import chess.view.input.command.MovePath;
import chess.view.output.OutputView;

public class PlayingGame implements GameStatus {

    private final Board board;
    private final Color color;

    public PlayingGame(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(final InputView inputView, final OutputView outputView, final ChessService chessService) {
        return applyCommand(inputView.getClientCommand(), outputView, chessService);
    }

    private GameStatus applyCommand(final ClientCommand clientCommand, final OutputView outputView,
                                    final ChessService chessService) {
        GameCommand gameCommand = clientCommand.getCommand();
        if (gameCommand == GameCommand.MOVE) {
            return movePiece(clientCommand.getMovePath(), outputView, chessService);
        }
        if (gameCommand == GameCommand.END) {
            return new SaveGame(board, color);
        }
        if (gameCommand == GameCommand.STATUS) {
            printCurrentStatus(outputView);
            return this;
        }
        if (gameCommand == GameCommand.START) {
            return new RestartGame();
        }
        throw new IllegalArgumentException("현재 상태에서 지원하지 않는 명령어 입니다.");
    }

    private void printCurrentStatus(final OutputView outputView) {
        GameResult gameResult = new GameResult(board);
        outputView.printGameResult(gameResult);
    }

    private GameStatus movePiece(final MovePath movePath, final OutputView outputView,
                                 final ChessService chessService) {
        Board board = this.board.move(movePath.from(), movePath.to(), color);
        if (board.isKingAlone()) {
            return new EndGame(board);
        }
        outputView.printBoard(board);
        chessService.saveChess(board, color);
        return new PlayingGame(board, color.opposite());
    }
}
