package chess.controller;

import chess.service.ChessGameService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class End implements GameState {

    @Override
    public GameState run(InputView inputView, OutputView outputView, ChessGameService chessGameService) {
        throw new UnsupportedOperationException("게임이 종료되어 게임을 실행할 수 없습니다.");
    }

    @Override
    public boolean canContinue() {
        return false;
    }
}
