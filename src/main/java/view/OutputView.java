package view;

import controller.dto.ChessBoardStatusDto;
import controller.dto.ScoreDto;
import java.util.List;

public class OutputView {

    private static final String SCORE_FORMAT = "%s 점수: %.1f, %s 점수: %.1f.";

    public void printGameGuide() {
        System.out.print("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 이전 게임 불러오기 : load
                > 현재 점수 확인 : status
                > 게임 저장 후 종료 : save
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                """);
    }

    public void printCurrentTurnColor(String colorName) {
        System.out.println(colorName + "이(가) 이동할 차례입니다.");
    }

    public void printBoard(ChessBoardStatusDto chessBoardStatusDTO) {
        chessBoardStatusDTO.boardStatus().forEach(this::printRow);
    }

    private void printRow(List<String> row) {
        row.forEach(System.out::print);
        System.out.println();
    }

    public void printScore(ScoreDto player, ScoreDto opponent) {
        System.out.println(formatScore(player, opponent));
    }

    private String formatScore(ScoreDto player, ScoreDto opponent) {
        return SCORE_FORMAT.formatted(player.colorName(), player.score(), opponent.colorName(), opponent.score());
    }

    public void printScoreResult(ScoreDto player, ScoreDto opponent) {
        System.out.println(
                formatScore(player, opponent) + " " + formatResult(player.score(), opponent.score())
        );
    }

    private String formatResult(double playerScore, double opponentScore) {
        if (playerScore > opponentScore) {
            return "승리하셨습니다!";
        }
        if (playerScore < opponentScore) {
            return "패배하셨습니다!";
        }
        return "비겼습니다.";
    }

    public void printMessageWhenRemoveOpponentKing(String removedKingColor, String winner) {
        System.out.println(removedKingColor + "의 KING이 제거되었습니다. " + winner + " 승리!");
    }
}
