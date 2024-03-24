package chess.domain.piece;

import static chess.domain.Position.A1;
import static chess.domain.Position.A7;
import static chess.domain.Position.B2;
import static chess.domain.Position.B6;
import static chess.domain.Position.C3;
import static chess.domain.Position.C5;
import static chess.domain.Position.D4;
import static chess.domain.Position.E3;
import static chess.domain.Position.E5;
import static chess.domain.Position.F2;
import static chess.domain.Position.F6;
import static chess.domain.Position.G1;
import static chess.domain.Position.G7;
import static chess.domain.Position.H8;
import static chess.domain.piece.PieceMoveResult.CATCH;
import static chess.domain.piece.PieceMoveResult.FAILURE;
import static chess.domain.piece.PieceMoveResult.SUCCESS;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.Position;
import chess.domain.board.ChessBoard;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {
    public static Stream<Arguments> moveFailureParameters() {
        Set<Position> successParameters = moveSuccessParameters().map(Arguments::get)
                .map(objects -> (Position) objects[0])
                .collect(Collectors.toSet());
        return Arrays.stream(Position.values())
                .filter(position -> !successParameters.contains(position))
                .map(Arguments::of);
    }

    public static Stream<Arguments> moveSuccessParameters() {
        return Stream.of(
                Arguments.of(C5), Arguments.of(B6), Arguments.of(A7),
                Arguments.of(E5), Arguments.of(F6), Arguments.of(G7),
                Arguments.of(H8), Arguments.of(C3), Arguments.of(B2),
                Arguments.of(A1), Arguments.of(E3), Arguments.of(F2),
                Arguments.of(G1)
        );
    }

    public static Stream<Arguments> moveFailureCauseRouteParameters() {
        return Stream.of(
                Arguments.of(H8, F6),
                Arguments.of(A7, B6),
                Arguments.of(A1, B2),
                Arguments.of(G1, F2)
        );
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("비숍의 이동 규칙대로 이동이 가능한지 검증")
    void moveSuccess(Position targetPosition) {
        Bishop bishop = new Bishop(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of());
        Assertions.assertThat(bishop.move(targetPosition, chessBoard))
                .isEqualTo(SUCCESS);
    }

    @ParameterizedTest
    @MethodSource("moveFailureParameters")
    @DisplayName("비숍의 이동 규칙을 위반한 이동이 불가능한지 검증")
    void moveFailure(Position targetPosition) {
        Bishop bishop = new Bishop(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of());
        Assertions.assertThat(bishop.move(targetPosition, chessBoard))
                .isEqualTo(FAILURE);
    }

    @ParameterizedTest
    @MethodSource("moveFailureCauseRouteParameters")
    @DisplayName("비숍의 이동 경로에 다른 말이 있는 경우 이동이 불가능한지 검증")
    void moveFailureCauseRoute(Position targetPosition, Position other) {
        Bishop bishop = new Bishop(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(other, WHITE)));
        Assertions.assertThat(bishop.move(targetPosition, chessBoard))
                .isEqualTo(FAILURE);
    }

    @ParameterizedTest
    @MethodSource("moveFailureCauseRouteParameters")
    @DisplayName("비숍의 이동 경로 뒤에 다른 말이 있는 경우 이동이 가능한지 검증")
    void moveSuccessCauseRoute(Position other, Position targetPosition) {
        Bishop bishop = new Bishop(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(other, WHITE)));
        Assertions.assertThat(bishop.move(targetPosition, chessBoard))
                .isEqualTo(SUCCESS);
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("비숍의 목적지에 같은 팀 말이 있는 경우 이동이 불가능한지 검증")
    void moveFailureCauseTargetIsSameTeam(Position targetPosition) {
        Bishop bishop = new Bishop(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(targetPosition, WHITE)));
        Assertions.assertThat(bishop.move(targetPosition, chessBoard))
                .isEqualTo(FAILURE);
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("비숍의 목적지에 다른 팀 말이 있는 경우 이동이 가능한지 검증")
    void moveSuccessWhenTargetIsOtherTeam(Position targetPosition) {
        Bishop bishop = new Bishop(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(targetPosition, BLACK)));
        Assertions.assertThat(bishop.move(targetPosition, chessBoard))
                .isEqualTo(CATCH);
    }
}
