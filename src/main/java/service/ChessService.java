package service;

import db.Movement;
import db.MovementDao;
import db.MovementDaoImpl;
import domain.board.Board;
import domain.board.BoardAdaptor;
import domain.board.BoardInitiator;
import domain.board.ChessGame;
import domain.board.position.Position;
import domain.piece.Color;
import java.sql.Connection;
import java.util.List;

public class ChessService {
    private final MovementDao movementDaoImpl;

    public ChessService(final Connection connection) {
        this.movementDaoImpl = new MovementDaoImpl(connection);
    }

    public boolean isRunning() {
        final ChessGame chessGame = getGame();
        return !chessGame.isKingDead();
    }

    public ChessGame getGame() {
        final List<Movement> positions = movementDaoImpl.findAll();
        final ChessGame game = new ChessGame(new BoardAdaptor(new Board(BoardInitiator.init())));
        for (final var movement : positions) {
            game.move(movement.source().toFileName().toLowerCase() + (movement.source().toRankIndex() + 1),
                    movement.target().toFileName().toLowerCase() + (movement.target().toRankIndex() + 1));
        }
        return game;
    }

    public void move(final String source, final String target) {
        final ChessGame game = getGame();
        game.move(source, target);
        movementDaoImpl.createMovement(
                new Movement(Position.from(String.valueOf(source.charAt(0)),
                        String.valueOf(Character.getNumericValue(source.charAt(1)))),
                        Position.from(String.valueOf(target.charAt(0)),
                                String.valueOf(Character.getNumericValue(target.charAt(1)))),
                        game.getPiece(target).getClass().getSimpleName(), game.getPiece(target).getColor().name()));
    }

    public Double calculateScore(final Color color) {
        return getGame().calculateScore(color);
    }

    public boolean isKingDeadOf(final Color color) {
        return getGame().isKingDeadOf(color);
    }

    public void deleteAll() {
        movementDaoImpl.deleteAll();
    }

    public Board getBoard() {
        final List<Movement> positions = movementDaoImpl.findAll();
        final Board board = new Board(BoardInitiator.init());
        for (final var movement : positions) {
            board.move(movement.source(), movement.target());
        }
        return board;
    }
}
