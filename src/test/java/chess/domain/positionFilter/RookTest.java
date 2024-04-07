package chess.domain.positionFilter;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.game.PositionsFilter;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("포지션이 비어있을 시 이동 가능한 포지션에 모두 포함되어야 한다.")
    void positionEmptyAllMovablePositionsIncludedTest() {
        Position position = new Position(Row.FIVE, Column.D);
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        Map<Direction, Queue<Position>> candidatePositions = piece.generateAllDirectionPositions(position);
        Board board = new Board(Map.of(position, piece));

        List<Position> movablePositions = PositionsFilter.generateValidPositions(candidatePositions, piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.SIX, Column.D),
                new Position(Row.SEVEN, Column.D),
                new Position(Row.EIGHT, Column.D),

                new Position(Row.FIVE, Column.E),
                new Position(Row.FIVE, Column.F),
                new Position(Row.FIVE, Column.G),
                new Position(Row.FIVE, Column.H),

                new Position(Row.FOUR, Column.D),
                new Position(Row.THREE, Column.D),
                new Position(Row.TWO, Column.D),
                new Position(Row.ONE, Column.D),

                new Position(Row.FIVE, Column.C),
                new Position(Row.FIVE, Column.B),
                new Position(Row.FIVE, Column.A)
        );
    }

    @Test
    @DisplayName("포지션에 상대 기물이 존재하면 이동할 수 있는 위치에 포함되어야 한다.")
    void positionWithOpponentPiecesTest() {
        Position position = new Position(Row.FIVE, Column.D);
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        Map<Direction, Queue<Position>> candidatePositions = piece.generateAllDirectionPositions(position);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.SIX, Column.D), new Piece(PieceType.BISHOP, Color.BLACK),
                        new Position(Row.FOUR, Column.D), new Piece(PieceType.KNIGHT, Color.BLACK),
                        new Position(Row.FIVE, Column.C), new Piece(PieceType.KNIGHT, Color.BLACK),
                        new Position(Row.FIVE, Column.E), new Piece(PieceType.BISHOP, Color.BLACK)
                )
        );

        List<Position> movablePositions = PositionsFilter.generateValidPositions(candidatePositions, piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.SIX, Column.D),
                new Position(Row.FOUR, Column.D),
                new Position(Row.FIVE, Column.C),
                new Position(Row.FIVE, Column.E)
        );
    }

    @Test
    @DisplayName("포지션에 우리팀 기물이 존재하면 이동가능한 위치에 포함되면 안된다.")
    void positionWithOwnPiecesTest() {
        Position position = new Position(Row.FIVE, Column.D);
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        Map<Direction, Queue<Position>> candidatePositions = piece.generateAllDirectionPositions(position);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.SIX, Column.D), new Piece(PieceType.BISHOP, Color.WHITE),
                        new Position(Row.FOUR, Column.D), new Piece(PieceType.KNIGHT, Color.WHITE),
                        new Position(Row.FIVE, Column.C), new Piece(PieceType.KNIGHT, Color.WHITE),
                        new Position(Row.FIVE, Column.E), new Piece(PieceType.BISHOP, Color.WHITE)
                )
        );

        List<Position> movablePositions = PositionsFilter.generateValidPositions(candidatePositions, piece, board);

        assertThat(movablePositions).isEmpty();
    }
}
