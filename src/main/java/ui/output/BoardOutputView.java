package ui.output;

import domain.board.ChessBoard;
import domain.board.Winner;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardOutputView {
    private static final List<String> RANK = List.of("8", "7", "6", "5", "4", "3", "2", "1");
    private static final List<String> FILE = List.of("a", "b", "c", "d", "e", "f", "g", "h");

    private static final Map<Type, String> PIECE_DISPLAY = Map.of(
            Type.PAWN, "p",
            Type.KNIGHT, "n",
            Type.BISHOP, "b",
            Type.ROOK, "r",
            Type.QUEEN, "q",
            Type.KING, "k",
            Type.EMPTY, "."
    );

    private static final Map<Winner, String> COLOR_DISPLAY = Map.of(
            Winner.WHITE, "흰색",
            Winner.BLACK, "검은색",
            Winner.DRAW, "무승부"
    );

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 저장된 체스 게임을 확인 중입니다.");
    }

    public void printCommandMessage() {
        System.out.println("> 기물 이동 : move source위치 target위치 - ex. move b2 b3");
        System.out.println("> 게임 상태 : status");
        System.out.println("> 게임 종료 : end - 종료 시 게임이 저장됩니다.");
    }

    public void printBoard(ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.getPositionAndPieces();
        RANK.stream()
                .map(rank -> generatePieceDisplay(board, rank))
                .forEach(System.out::println);
    }

    private String generatePieceDisplay(Map<Position, Piece> board, String rank) {
        return FILE.stream()
                .map(file -> Position.from(file + rank))
                .map(position -> board.getOrDefault(position, Empty.create()))
                .map(this::pieceDisplay)
                .collect(Collectors.joining());
    }

    private String pieceDisplay(Piece piece) {
        String pieceName = PIECE_DISPLAY.get(piece.type());
        if (piece.isSameColor(Color.BLACK)) {
            return pieceName.toUpperCase();
        }
        return pieceName;
    }

    public void printBoardStatus(ChessBoard chessBoard) {
        System.out.printf("흰색의 점수: %.1f%n", chessBoard.calculateScore(Color.WHITE));
        System.out.printf("검은색의 점수: %.1f%n", chessBoard.calculateScore(Color.BLACK));
        System.out.println();
    }

    public void printKingCapturedMessage(Winner winner) {
        System.out.printf("> %s이 승리했습니다.%n", COLOR_DISPLAY.get(winner));
        System.out.println();
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
