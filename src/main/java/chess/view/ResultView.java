package chess.view;

import static chess.view.command.CommandType.END;

import chess.view.dto.ChessResultDto;
import chess.view.dto.ChessboardDto;

public class ResultView {

    public void printGameStartMessage() {
        System.out.printf("체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
    }

    public void printGameEnd() {
        System.out.printf(END.getMessage());
    }

    public void printBoard(final ChessboardDto chessboardDto) {
        System.out.println(chessboardDto.getChessboard());
    }

    public void printCheckmate() {
        System.out.printf("%n체크메이트. 'status'를 입력하면 게임 결과를 확인할 수 있습니다.%n");
    }

    public void printResult(final ChessResultDto chessResultDto) {
        System.out.printf("%n> 결과%n"
                + chessResultDto.scores()
                + "%n"
                + chessResultDto.winner()
                + "의 승리입니다!%n");
    }
}
