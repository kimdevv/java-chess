package chess;

import chess.piece.Piece;
import chess.player.Player;
import chess.player.Team;
import chess.position.Fixtures;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class ChessConsoleManager {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessConsoleManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runChess() {
        Player whitePlayer = Player.generateWithPiecesFromTeam(Team.WHITE);
        Player blackPlayer = Player.generateWithPiecesFromTeam(Team.BLACK);
        Queue<Player> queue = new ArrayDeque();
        queue.add(whitePlayer);
        queue.add(blackPlayer);
        while(true) {
            outputView.outputCurrentChessBoard(blackPlayer.getPieces(), whitePlayer.getPieces());
            Player currentPlayer = queue.poll();
            Player otherPlayer = queue.peek();
            Position startPosition = Fixtures.positionOf(inputView.inputPiecePositionToMove(currentPlayer.getTeam()));
            Piece piece = currentPlayer.findPiece(startPosition);
            Position destination = Fixtures.positionOf(inputView.inputDestinationToMove(piece, currentPlayer.getTeam()));
            if (currentPlayer.isExistPieceAt(destination)) {
                throw new IllegalArgumentException("현재 이동할 수 없는 위치입니다.");
            }
            List<Position> route = piece.calculateRouteToDestination(destination);
            route.removeLast();
            if (otherPlayer.isExistPieceAtRoute(route)) {
                throw new IllegalArgumentException("현재 이동할 수 없는 위치입니다.");
            }
            if (otherPlayer.isExistPieceAt(destination)) {
                Piece removedPiece = otherPlayer.removeAndGetPieceAt(destination);
                if (removedPiece.isKing()) {
                    outputView.outputWinnerCongratulation(currentPlayer.getTeam());
                    break;
                }
            }
            piece.changePosition(destination);
            queue.add(currentPlayer);
        }
    }
}
