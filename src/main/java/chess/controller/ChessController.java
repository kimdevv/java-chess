package chess.controller;

import chess.dao.ChessDaoService;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import chess.dto.PieceDto;
import chess.dto.TurnDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {
    private final ChessDaoService chessDaoService;
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final ChessDaoService chessDaoService, final InputView inputView, final OutputView outputView) {
        this.chessDaoService = chessDaoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();

        final ChessGame game = initializeChessGame();

        while (game.isPlayable()) {
            play(game);
        }
        updateGameStatus(game);
    }

    private ChessGame initializeChessGame() {
        if (chessDaoService.isPreviousDataExist()) {
            final TurnDto turnDto = chessDaoService.loadPreviousTurn();
            return new ChessGame(BoardFactory.loadPreviousChessBoard(chessDaoService.loadPreviousData()), turnDto.getTurn());
        }
        return new ChessGame(BoardFactory.createInitialChessBoard());
    }

    private void play(final ChessGame game) {
        try {
            final List<String> readCommand = inputView.readCommand();
            final Command command = Command.from(readCommand.get(0));

            executeCommand(game, command, readCommand);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void executeCommand(final ChessGame game, final Command command, final List<String> readCommand) {
        if (command.isEnd()) {
            game.finish();
            return;
        }
        if (command.isStart()) {
            game.start();
        }
        if (command.isMove()) {
            final Point departure = generatePoint(readCommand, 1);
            final Point destination = generatePoint(readCommand, 2);
            game.move(departure, destination);
        }
        if (command.isStatus()) {
            final double whiteScore = game.calculateScore(Team.WHITE);
            final double blackScore = game.calculateScore(Team.BLACK);

            outputView.printScore(whiteScore, blackScore);
            return;
        }
        outputView.printBoard(game.getBoard());
    }

    private Point generatePoint(final List<String> readCommand, final int index) {
        final String pointInfo = readCommand.get(index);
        final char file = pointInfo.charAt(0);
        final int rank = Character.getNumericValue(pointInfo.charAt(1));

        return Point.of(file, rank);
    }

    private void updateGameStatus(final ChessGame game) {
        if (game.isGameOver()) {
            chessDaoService.deletePreviousData();
            return;
        }
        final List<PieceDto> pieceDtos = getPieces(game);
        chessDaoService.updatePiece(pieceDtos);
        chessDaoService.updateTurn(TurnDto.from(game.getTeam()));
    }

    private List<PieceDto> getPieces(final ChessGame game) {
        final Map<Point, Piece> board = game.getBoard();

        return board.entrySet()
                .stream()
                .map(entry -> PieceDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }
}
