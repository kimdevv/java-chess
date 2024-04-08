package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final int MINIMUM_FILE_RANGE = 1;
    private static final int MAXIMUM_FILE_RANGE = 8;
    private static final int MINIMUM_RANK_RANGE = 1;
    private static final int MAXIMUM_RANK_RANGE = 8;

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        String commandMessage = Arrays.stream(GameCommand.values())
                .map(gameCommand -> "> %s".formatted(gameCommand.getHelperMessage()))
                .collect(Collectors.joining("\n"));

        System.out.println(commandMessage);
    }

    public void printBoard(final Map<Position, Piece> positions) {
        for (int rank = MAXIMUM_RANK_RANGE; rank >= MINIMUM_RANK_RANGE; rank--) {
            printRankLine(positions, rank);
        }
        System.out.println();
    }

    private void printRankLine(final Map<Position, Piece> positions, final int rank) {
        String rankLine = IntStream.rangeClosed(MINIMUM_FILE_RANGE, MAXIMUM_FILE_RANGE)
                .boxed()
                .map(file -> positions.get(Position.of(file, rank)))
                .map(PieceSymbol::getDisplay)
                .collect(Collectors.joining(""));
        System.out.println(rankLine);
    }

    public void printScore(final double teamScore, final Color color) {
        System.out.println(color + "팀의 현재 점수는 " + teamScore + "입니다.");
    }

    public void printFinish() {
        System.out.println("King이 잡혔습니다. 게임을 종료합니다.");
    }

    public void printCurrentTurn(final Color color) {
        System.out.println(ColorDisplay.getValue(color) + "팀 차례입니다.");
    }
}
