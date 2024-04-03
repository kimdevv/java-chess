package view;

import dto.ChessBoardDto;
import dto.StatusDto;

import java.util.List;

public class OutputView {
    public void printHeader() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printChessBoard(final ChessBoardDto chessBoardDTO) {
        final List<String> pieces = chessBoardDTO.pieces();
        for (int i = 0; i < pieces.size(); i += 8) {
            final int end = Math.min(i + 8, pieces.size());
            final List<String> sublist = pieces.subList(i, end);
            System.out.println(String.join("", sublist));
        }
    }

    public void printError(final String message) {
        System.out.println(message);
    }

    public void printStatus(final StatusDto status) {
        System.out.println("백 진영 점수 : " + status.whiteScore());
        System.out.println("흑 진영 점수 : " + status.blackScore());

        String winLose = "무승부";
        if (status.whiteScore() > status.blackScore()) {
            winLose = "백 진영 승리";
        }
        if (status.whiteScore() < status.blackScore()) {
            winLose = "흑 진영 승리";
        }
        System.out.println(winLose);
    }

    public void printEndMessage() {
        System.out.println("게임을 종료합니다.");
    }
}
