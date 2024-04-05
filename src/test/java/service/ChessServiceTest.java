package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import db.InMemoryDao;
import db.Movement;
import db.MovementDao;
import domain.board.Board;
import domain.board.BoardAdaptor;
import domain.board.BoardInitiator;
import domain.board.ChessGame;
import domain.board.position.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {


    final MovementDao movementDao = new InMemoryDao();

    @BeforeEach
    void before() {
        movementDao.findAll().clear();
    }

    @Test
    void getGame() {
        final List<Movement> positions = movementDao.findAll();
        final ChessGame game = new ChessGame(new BoardAdaptor(new Board(BoardInitiator.init())));
        for (final var movement : positions) {
            game.move(movement.source().toString(), movement.target().toString());
        }
    }

    @Test
    @DisplayName("움직였을 때 데이터가 생성되었는지 확인한다")
    public void move() {
        final String source = "b2";
        final String target = "b3";
        final List<Movement> positions = movementDao.findAll();
        final ChessGame game = new ChessGame(new BoardAdaptor(new Board(BoardInitiator.init())));
        for (final var movement : positions) {
            game.move(movement.source().toString(), movement.target().toString());
        }
        game.move(source, target);
        movementDao.createMovement(
                new Movement(Position.from(String.valueOf(source.charAt(0)), String.valueOf(source.charAt(1))),
                        Position.from(String.valueOf(target.charAt(0)), String.valueOf(target.charAt(1))),
                        game.getPiece(target).getClass().getSimpleName(), game.getPiece(target).getColor().name()));
        assertThat(movementDao.findAll().size()).isEqualTo(1);
    }


    @Test
    @DisplayName("데이터가 모두 지워졌는지 확인한다")
    void deleteAll() {
        movementDao.deleteAll();
        assertThat(movementDao.findAll().size()).isEqualTo(0);
    }
}
