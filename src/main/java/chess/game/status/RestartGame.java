package chess.game.status;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class RestartGame implements GameStatus {
    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(final InputView inputView, final OutputView outputView, final ChessService chessService) {
        outputView.printRestartMessage();
        Board board = BoardFactory.create();
        Color color = Color.FIRST_TURN_COLOR;

        outputView.printBoard(board);
        chessService.saveChess(board, color);
        
        return new PlayingGame(board, color);
    }
}
