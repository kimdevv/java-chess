package chess.game.status;

import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.dto.ChessDTO;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class StartGame implements GameStatus {

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(final InputView inputView, final OutputView outputView, final ChessService chessService) {
        ChessDTO chessDTO = loadFromExternal(outputView, chessService);
        outputView.printBoard(chessDTO.board());
        return new PlayingGame(chessDTO.board(), chessDTO.color());
    }

    private ChessDTO loadFromExternal(final OutputView outputView, final ChessService chessService) {
        try {
            return chessService.loadChess();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            outputView.printErrorMessage("기본 보드로 시작합니다.");
            return new ChessDTO(BoardFactory.create(), Color.FIRST_TURN_COLOR);
        }
    }
}
