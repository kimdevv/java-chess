package chess.view;

import chess.dao.TurnDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.GameScore;
import chess.domain.chessgame.gamecommand.GameCommandState;
import chess.domain.chessgame.gamecommand.MoveState;
import chess.domain.chessgame.gamecommand.StartState;
import chess.domain.chessgame.gamecommand.StatusState;
import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceType;
import chess.dto.ChessBoardDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.printf("%s 체스 게임을 시작합니다. %s", ">", LINE_SEPARATOR);
        System.out.printf("%s 게임 시작 %s start %s", ">", ":", LINE_SEPARATOR);
        System.out.printf("%s 게임 종료 %s end %s", ">", ":", LINE_SEPARATOR);
        System.out.printf("%s 게임 이동 %s move source위치 target위치 - 예. move b2 b3 %s", ">", ":", LINE_SEPARATOR);
    }

    public static void printGameExecute(GameCommandState gameCommandState, ChessBoard chessBoard) {
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard);
        if (StartState.getInstance().isStartState(gameCommandState)) {
            printChessBoard(chessBoardDto);
        }
        if (MoveState.getInstance().isMoveState(gameCommandState)) {
            printChessBoard(chessBoardDto);
            printTurn(new TurnDao().findTurn());
        }
        if (StatusState.getInstance().isStatusState(gameCommandState)) {
            printGameStatus(chessBoard);
        }
    }

    private static void printChessBoard(ChessBoardDto chessBoardDto) {
        Map<Square, ChessPiece> chessBoard = chessBoardDto.chessBoard();
        List<Numbering> numbering = reverseNumbering();

        for (Numbering number : numbering) {
            List<Square> chessRow = selectChessRow(number, chessBoard);
            printSquare(chessRow, chessBoard);
            System.out.println();
        }
    }

    private static List<Numbering> reverseNumbering() {
        List<Numbering> numbering = new ArrayList<>(List.of(Numbering.values()));
        Collections.reverse(numbering);
        return numbering;
    }

    private static List<Square> selectChessRow(Numbering number, Map<Square, ChessPiece> chessBoard) {
        return chessBoard.keySet().stream()
                .filter(square -> square.getNumbering() == number)
                .toList();
    }

    private static void printSquare(List<Square> chessRow, Map<Square, ChessPiece> chessBoard) {
        for (Square square : chessRow) {
            ChessPiece chessPiece = chessBoard.get(square);
            printSquareWithChessPiece(chessPiece);
        }
    }

    private static void printSquareWithChessPiece(ChessPiece chessPiece) {
        String chessPieceNotation = ChessPiecePrintFormat.findChessPieceNotation(chessPiece);
        System.out.print(chessPieceNotation);
    }

    private static void printGameStatus(ChessBoard chessBoard) {
        GameScore blackGameScore = new GameScore(chessBoard, Camp.BLACK);
        GameScore whiteGameScore = new GameScore(chessBoard, Camp.WHITE);
        Camp winCamp = blackGameScore.winCamp(whiteGameScore);
        System.out.println(Camp.BLACK + " 의 점수는 " + blackGameScore.getTotalScore());
        System.out.println(Camp.WHITE + " 의 점수는 " + whiteGameScore.getTotalScore());
        printWinLoss(winCamp);
    }

    private static void printWinLoss(Camp camp) {
        if (camp.isBlackCamp() || camp.isWhiteCamp()) {
            System.out.println("현재 " + camp + " 진영이 이기고 있습니다.");
        }
        if (camp.isNoneCamp()) {
            System.out.println("현재 무승부입니다.");
        }
    }

    public static void printWhenKingDead(Camp camp) {
        System.out.println(ChessPieceType.KING + " 이 잡혔습니다.");
        System.out.println(camp + " 진영이 이겼습니다.");
    }

    private static void printTurn(Camp camp) {
        if (!camp.isNoneCamp()) {
            System.out.println(camp + " 진영의 차례를 마쳤습니다.");
            System.out.println(camp.findEnemyCamp() + " 진영의 차례입니다.");
        }
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
