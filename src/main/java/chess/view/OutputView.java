package chess.view;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    private static final Map<Piece, String> pieceBoard = initializePiece();
    private static final Map<Team, String> teamBoard = initializeTeam();

    public static void printChessBoard(Map<Position, Piece> chessBoard) {
        for (int i = 8; i >= 1; i--) {
            printOneRank(chessBoard, i);
        }
        System.out.println();
    }

    private static void printOneRank(Map<Position, Piece> chessBoard, int rankIndex) {
        for (int j = 1; j <= 8; j++) {
            Position position = new Position(File.from(j), Rank.from(rankIndex));
            System.out.print(getPiece(chessBoard, position));
        }
        System.out.println();
    }

    private static String getPiece(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            Piece piece = chessBoard.get(position);
            return pieceBoard.get(piece);
        }
        return ".";
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3`");
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printScore(Map<Team, Score> calculateTotalScore) {
        double whiteScore = calculateTotalScore.get(WHITE)
                .getScore();
        double blackScore = calculateTotalScore.get(BLACK)
                .getScore();

        System.out.println(String.format("%s팀 점수 : %.1f", WHITE, whiteScore));
        System.out.println(String.format("%s팀 점수 : %.1f", BLACK, blackScore));

        if (whiteScore > blackScore) {
            System.out.println("백팀 승");
        }
        if (whiteScore == blackScore) {
            System.out.println("무승부");
        }
        if (whiteScore < blackScore) {
            System.out.println("흑팀 승");
        }
    }

    private static Map<Piece, String> initializePiece() {
        Map<Piece, String> pieceBoard = new HashMap<>();
        pieceBoard.put(new King(BLACK), "K");
        pieceBoard.put(new King(WHITE), "k");
        pieceBoard.put(new Queen(BLACK), "Q");
        pieceBoard.put(new Queen(WHITE), "q");
        pieceBoard.put(new Rook(BLACK), "R");
        pieceBoard.put(new Rook(WHITE), "r");
        pieceBoard.put(new Bishop(BLACK), "B");
        pieceBoard.put(new Bishop(WHITE), "b");
        pieceBoard.put(new Knight(BLACK), "N");
        pieceBoard.put(new Knight(WHITE), "n");
        pieceBoard.put(new BlackPawn(), "P");
        pieceBoard.put(new WhitePawn(), "p");
        return pieceBoard;
    }

    private static Map<Team, String> initializeTeam() {
        Map<Team, String> teamBoard = new HashMap<>();
        teamBoard.put(WHITE, "백");
        teamBoard.put(BLACK, "흑");

        return teamBoard;
    }

    public static void printGameOverMessage() {
        System.out.println("> 게임이 종료되었습니다.");
        System.out.println("> 결과 확인 : status");
    }
}
