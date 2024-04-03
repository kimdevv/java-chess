package service;

import dao.ChessBoardDao;
import dao.ConnectionPool;
import dao.TurnDao;
import domain.*;
import domain.piece.Piece;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessServiceTest {
    ChessService chessService;

    final Map<Square, Piece> pieces = new HashMap<>(
            Map.of(new Square(File.E, Rank.SEVEN), new BlackPawn(),
                    new Square(File.F, Rank.SIX), new WhitePawn())
    );

    final Team team = Team.WHITE;

    @BeforeEach
    void setUp() {
        final MockChessBoardDao mockChessBoardDao = new MockChessBoardDao(pieces);
        final MockTurnDao mockTurnDao = new MockTurnDao(team);
        chessService = new ChessService(mockChessBoardDao, mockTurnDao);
    }

    @Test
    void start() {
        // when
        chessService.start();
        // then
        assertThat(chessService.getChessBoard()).isEqualTo(pieces);
    }

    @Test
    void move() {
        // given
        chessService.start();
        // when
        chessService.move(new Square(File.F, Rank.SIX), new Square(File.E, Rank.SEVEN));
        // then
        assertThat(chessService.getChessBoard().get(new Square(File.E, Rank.SEVEN)))
                .isEqualTo(new WhitePawn());
    }

    @Test
    void status() {
        // given
        chessService.start();
        // when
        final Map<Team, Double> status = chessService.status();
        // then
        assertThat(status).isEqualTo(Map.of(Team.WHITE, 1.0, Team.BLACK, 1.0));
    }

    @Test
    void isFinished() {
        // given
        chessService.start();
        // when
        final boolean isFinished = chessService.isFinished();
        // then
        assertThat(isFinished).isTrue();
    }

    @Test
    void end() throws SQLException {
        // given
        chessService.start();
        chessService.move(new Square(File.F, Rank.SIX), new Square(File.E, Rank.SEVEN));
        // when
        chessService.end();
        chessService.start();
        // then
        assertThat(chessService.getChessBoard())
                .isEqualTo(Map.of(new Square(File.E, Rank.SEVEN), new WhitePawn()));
    }
}


class MockChessBoardDao extends ChessBoardDao {
    Map<Square, Piece> pieces;

    public MockChessBoardDao(final Map<Square, Piece> pieces) {
        super(new MockConnectionPool());
        this.pieces = pieces;
    }

    @Override
    public Map<Square, Piece> findAll() {
        return pieces;
    }

    @Override
    public void update(final ChessBoard chessBoard) {
        this.pieces = chessBoard.getPieces();
    }
}

class MockTurnDao extends TurnDao {
    Team team;

    public MockTurnDao(final Team team) {
        super(new MockConnectionPool());
        this.team = team;
    }

    @Override
    public Team find() {
        return Team.WHITE;
    }

    @Override
    public void update(final Team team) {
        this.team = team;
    }
}


class MockConnectionPool implements ConnectionPool {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void releaseConnection(final Connection connection) {
    }
}
