package controller;

import domain.piece.Color;
import dto.DtoMapper;
import java.sql.Connection;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import service.ChessService;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final Connection connection;

    public ChessController(final Connection connection) {
        this.connection = connection;
    }

    public void start() {
        final ChessService chessService = new ChessService(connection);
        retryUntilNoException(this::handleStart, chessService);
        retryUntilNoException(this::handleMove, chessService);

    }

    private void handleStart(final ChessService chessService) {
        final String command = InputView.inputCommand();
        if (!Commands.START.pattern.matcher(command).matches()) {
            throw new IllegalArgumentException("게임을 시작해주세요");
        }
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessService.getBoard()));
    }

    private void handleMove(final ChessService chessService) {
        while (chessService.isRunning()) {
            final String command = InputView.inputCommand();
            if (Commands.END.pattern.matcher(command).matches()) {
                break;
            }

            runCommand(chessService, command);
        }
    }

    private <T> T retryUntilNoException(final Consumer<ChessService> consumer, final ChessService chessService) {
        try {
            consumer.accept(chessService);
        } catch (final IllegalArgumentException | UnsupportedOperationException | NoSuchElementException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return retryUntilNoException(consumer, chessService);
        }
        return null;
    }

    private void runCommand(final ChessService chessService, final String command) {
        if (Commands.STATUS.pattern.matcher(command).matches()) {
            OutputView.printChessResult(DtoMapper.generateGameResultResponse(chessService.calculateScore(Color.WHITE),
                    chessService.calculateScore(Color.BLACK), chessService.isKingDeadOf(Color.WHITE),
                    chessService.isKingDeadOf(Color.BLACK)));
            return;
        }
        if (Commands.MOVE.pattern.matcher(command).matches()) {
            final StringTokenizer tokens = skipFirstToken(command);
            chessService.move(tokens.nextToken(), tokens.nextToken());
            printChessBoardIfRunning(chessService);

            handleGameEnd(chessService);
            return;
        }
        if (Commands.RESET.pattern.matcher(command).matches()) {
            chessService.deleteAll();
            printChessBoardIfRunning(chessService);
            return;
        }
        throw new IllegalArgumentException("제대로된 명령어가 아닙니다");
    }

    private void handleGameEnd(final ChessService chessService) {
        if (!chessService.isRunning()) {
            OutputView.printChessResult(
                    DtoMapper.generateGameResultResponse(chessService.calculateScore(Color.WHITE),
                            chessService.calculateScore(Color.BLACK), chessService.isKingDeadOf(Color.WHITE),
                            chessService.isKingDeadOf(Color.BLACK)));
            chessService.deleteAll();
            OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessService.getBoard()));
        }
    }

    private void printChessBoardIfRunning(final ChessService chessService) {
        if (chessService.isRunning()) {
            OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessService.getBoard()));
        }
    }

    private StringTokenizer skipFirstToken(final String command) {
        final StringTokenizer stringTokenizer = new StringTokenizer(command);
        stringTokenizer.nextToken();
        return stringTokenizer;
    }

    enum Commands {
        START(Pattern.compile("start")),
        MOVE(Pattern.compile("move [a-h][0-8] [a-h][0-8]")),
        END(Pattern.compile("end")),
        STATUS(Pattern.compile("status")),
        RESET(Pattern.compile("reset"));
        Pattern pattern;

        Commands(final Pattern pattern) {
            this.pattern = pattern;
        }
    }
}
