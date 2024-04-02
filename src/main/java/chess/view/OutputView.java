package chess.view;

import chess.domain.dto.BoardPrintDto;
import java.util.List;

public class OutputView {

    public static void printBoard(BoardPrintDto boardPrintDto) {
        List<List<String>> rawBoard = boardPrintDto.board();

        System.out.printf("%n");
        for (int i = 7; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(rawBoard.get(i).get(j));
            }
            System.out.println(stringBuilder);
        }
        System.out.printf("%n");
    }

    public static void printChessGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public static void printCommandGuideMessage() {
        System.out.printf("> 새로운 게임 시작 : start%n"
                + "> 지난 게임 불러오기 : load%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n"
                + "> 각 진영의 점수 출력 : status%n"
                + "> 진행 상황 저장 : save%n"
                + "> 게임 종료 : end%n");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.printf("[ERORR] %s%n", errorMessage);
    }

    public static void printScoreSum(double blackPiecesScoreSum, double whitePiecesScoreSum) {
        System.out.printf("%nBLACK 팀 점수: %.1f%n" + "WHITE 팀 점수: %.1f%n", blackPiecesScoreSum, whitePiecesScoreSum);
    }

    public static void printWinnerTeam(double blackPiecesScoreSum, double whitePiecesScoreSum) {
        if (blackPiecesScoreSum > whitePiecesScoreSum) {
            System.out.printf("이긴 진영: %s%n", "BLACK 팀");
        } else if (blackPiecesScoreSum < whitePiecesScoreSum) {
            System.out.printf("이긴 진영: %s%n", "WHITE 팀");
        } else if (blackPiecesScoreSum == whitePiecesScoreSum) {
            System.out.printf("BLACK 팀과 WHITE 팀의 점수가 같습니다.%n");
        }
    }

    public static void printWhoTurn(String turn) {
        System.out.printf("%s 차례입니다.%n", turn);
    }
}
