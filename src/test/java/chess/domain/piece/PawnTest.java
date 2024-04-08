package chess.domain.piece;

import static chess.domain.pixture.PieceFixture.BLACK_QUEEN;
import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
import static chess.domain.pixture.PieceFixture.WHITE_QUEEN;
import static chess.domain.pixture.PositionFixture.WHITE_PAWN_FIRST_MOVE_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    // p** <- (1,3)
    // p** <- (1,4)
    // P**
    @Test
    @DisplayName("폰이 직선으로 이동할 경우 경로에 어떠한 말도 없다면 이동 가능하다.")
    void canMoveStraight() {
        assertAll(
                () -> assertThat(WHITE_PAWN.getPiece()
                        .canMove(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), Position.of(1, 3),
                                Map.of(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(),
                                        WHITE_PAWN.getPiece()))).isTrue(),

                () -> assertThat(WHITE_PAWN.getPiece()
                        .canMove(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(), Position.of(1, 4),
                                Map.of(WHITE_PAWN_FIRST_MOVE_POSITION.getPosition(),
                                        WHITE_PAWN.getPiece()))).isTrue());
    }

    // ****
    // *p*p <- target
    // **P* <- source
    @Test
    @DisplayName("폰이 대각선 이동할 경우 도착 위치에 상대방의 말이 있다면 이동 가능하다.")
    void canMoveDiagonal() {
        Position source = Position.of(3, 1);
        assertAll(
                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(2, 2),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(2, 2), BLACK_QUEEN.getPiece()))).isTrue(),

                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(4, 2),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(4, 2), BLACK_QUEEN.getPiece()))).isTrue());
    }

    // ****
    // p*** <- target(p, P)
    // p*** <- source
    @Test
    @DisplayName("폰이 직선으로 이동할 경우 도착 위치에 말이 있다면 이동이 불가능하다.")
    void canNotMoveStraightWhenPieceExistInTarget() {
        Position source = Position.of(1, 1);
        assertAll(
                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 2),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(1, 2), WHITE_QUEEN.getPiece()))).isFalse(),

                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 2),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(1, 2), BLACK_QUEEN.getPiece()))).isFalse());
    }


    // p*** <- target(p, P)
    // q***
    // p*** <- source
    @Test
    @DisplayName("폰이 직선으로 이동할 경우 도착 위치 전의 경로에 말이 있다면 이동이 불가능하다.")
    void canNotMoveStraightWhenPieceExistInPath() {
        Position source = Position.of(1, 1);
        assertAll(
                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 3),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(1, 2), BLACK_QUEEN.getPiece(),
                                Position.of(1, 3), Empty.getInstance()))).isFalse(),
                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(1, 3),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(1, 2), WHITE_QUEEN.getPiece(),
                                Position.of(1, 3), Empty.getInstance()))).isFalse());
    }

    // *p*p <- target
    // **p* <- source
    @Test
    @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 같은 색의 말인 경우 이동이 불가능하다.")
    void canNotMoveDiagonalWhenTargetIsSameColor() {
        Position source = Position.of(3, 1);
        assertAll(
                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(2, 2),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(2, 2), WHITE_QUEEN.getPiece()))).isFalse(),
                () -> assertThat(WHITE_PAWN.getPiece().canMove(source, Position.of(4, 2),
                        Map.of(source, WHITE_PAWN.getPiece(),
                                Position.of(4, 2), WHITE_QUEEN.getPiece()))).isFalse());
    }

    // *E*E <- target(EMPTY)
    // **p* <- source
    @Test
    @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 비어있을 경우 이동이 불가능하다.")
    void canNotMoveDiagonalWhenTargetIsEmpty() {
        assertAll(
                () -> assertThat(WHITE_PAWN.getPiece().canMove(Position.of(3, 1), Position.of(2, 2),
                        Map.of(Position.of(3, 1), WHITE_PAWN.getPiece(),
                                Position.of(2, 2), Empty.getInstance()))).isFalse(),

                () -> assertThat(
                        WHITE_PAWN.getPiece().canMove(Position.of(3, 1), Position.of(4, 2),
                                Map.of(Position.of(3, 1), WHITE_PAWN.getPiece(),
                                        Position.of(4, 2), Empty.getInstance()))).isFalse());
    }
}
