package chess.view;

import chess.domain.board.Board;
import chess.domain.game.ChessGameResult;
import chess.domain.game.ChessGameStatus;
import chess.domain.game.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int MAX_ROW = 8;
    private static final int MAX_COLUMN = 8;
    private static final String EMPTY_SQUARE = "•";
    private static final String ERROR_PREFIX = "[ERROR]";

    private OutputView() {
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.printf("%s %s%s", ERROR_PREFIX, errorMessage, NEW_LINE);
    }

    public static void printGameStartMessage() {
        String message = "> 체스 게임을 시작합니다." + NEW_LINE
                + "> 게임 시작 : start" + NEW_LINE
                + "> 게임 종료 : end" + NEW_LINE
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + NEW_LINE
                + "> 게임 점수 및 결과 확인 : status";

        System.out.println(message);
    }

    public static void printChessBoard(Board board, Color currentColor) {
        List<List<String>> boardDisplays = createBoardChecker();
        putPieces(board, boardDisplays);

        printCurrentColorMessage(currentColor);
        boardDisplays.forEach(boardPieces -> System.out.println(String.join(" ", boardPieces)));
    }

    private static void printCurrentColorMessage(Color currentColor) {
        if (currentColor == Color.NONE) {
            return;
        }

        System.out.printf("  [ %s 턴 ]%n", currentColor);
    }

    private static List<List<String>> createBoardChecker() {
        List<List<String>> boardChecker = new ArrayList<>();
        for (int row = 0; row < MAX_ROW; row++) {
            List<String> boardRowChecker = new ArrayList<>(Collections.nCopies(MAX_COLUMN, EMPTY_SQUARE));
            boardChecker.add(boardRowChecker);
        }

        return boardChecker;
    }

    private static void putPieces(Board board, List<List<String>> boardDisplays) {
        board.getPieces().forEach((position, piece) -> {
            int file = position.getFile() - 1;
            int rank = position.getRank() - 1;
            String pieceDisplay = PieceDisplay.convert(piece);
            boardDisplays.get(MAX_ROW - rank - 1).set(file, pieceDisplay);
        });
    }

    public static void printChessGameStatus(ChessGameStatus chessGameStatus) {
        double whiteScore = chessGameStatus.whiteScore();
        double blackScore = chessGameStatus.blackScore();

        String message = String.format("흰색 점수: %.1f, 검은색 점수: %.1f", whiteScore, blackScore);

        System.out.println(message);
    }

    public static void printGameResult(ChessGameResult gameResult) {
        if (gameResult == ChessGameResult.WHITE_WIN) {
            System.out.println("흰색 승리");
            return;
        }

        System.out.println("검은색 승리");
    }
}
