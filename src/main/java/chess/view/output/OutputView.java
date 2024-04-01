package chess.view.output;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.result.GameResult;
import chess.domain.result.Score;
import chess.view.input.command.GameCommand;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WINNER_FORMAT = "%s이 이겼습니다!";
    private static final String GAME_RESULT_FORMAT = "흰색 : %s점 검은색 : %s점 - 현재 유리한 팀 : %s";
    private static final DecimalFormat SCORE_FORMAT = new DecimalFormat("0.##");
    private static final String SAVE_SUCCESS = "성공적으로 게임이 저장 되었습니다.";

    public void printBoard(final Board board) {
        Map<Position, Piece> positions = board.getBoard();
        Arrays.stream(Rank.values()).sorted(Collections.reverseOrder()).forEach(rank -> printRankLine(positions, rank));
        System.out.println();
    }

    private void printRankLine(final Map<Position, Piece> positions, final Rank rank) {
        String rankLine = Arrays.stream(File.values())
                .map(file -> positions.get(Position.of(file, rank)))
                .map(PieceSymbol::getDisplay)
                .collect(Collectors.joining(""));
        System.out.println(rankLine);
    }

    public void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        String commandMessage = Arrays.stream(GameCommand.values())
                .map(gameCommand -> "> %s".formatted(gameCommand.getHelperMessage()))
                .collect(Collectors.joining("\n"));

        System.out.println(commandMessage);
    }

    public void printWinnerColor(final Color color) {
        ColorSymbol colorSymbol = ColorSymbol.getColorSymbol(color);
        System.out.println(WINNER_FORMAT.formatted(colorSymbol.getSymbol()));
    }

    public void printGameResult(final GameResult gameResult) {
        Color winnerColor = gameResult.getWinnerColor();
        ColorSymbol colorSymbol = ColorSymbol.getColorSymbol(winnerColor);
        Score blackScore = gameResult.calcuateScore(Color.BLACK);
        Score whiteScore = gameResult.calcuateScore(Color.WHITE);

        System.out.println(GAME_RESULT_FORMAT.formatted(applyFormat(whiteScore), applyFormat(blackScore),
                colorSymbol.getSymbol()));
    }

    private String applyFormat(final Score score) {
        return SCORE_FORMAT.format(score.getValue());
    }

    public void printSave() {
        System.out.println(SAVE_SUCCESS);
    }

    public void printRestartMessage() {
        System.out.println("게임을 재 시작합니다.");
    }
}
