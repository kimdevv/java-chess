package chess.domain.board;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.A2;
import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.A8;
import static chess.domain.Fixtures.B1;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.B7;
import static chess.domain.Fixtures.B8;
import static chess.domain.Fixtures.C1;
import static chess.domain.Fixtures.C2;
import static chess.domain.Fixtures.C7;
import static chess.domain.Fixtures.C8;
import static chess.domain.Fixtures.D1;
import static chess.domain.Fixtures.D2;
import static chess.domain.Fixtures.D7;
import static chess.domain.Fixtures.D8;
import static chess.domain.Fixtures.E1;
import static chess.domain.Fixtures.E2;
import static chess.domain.Fixtures.E7;
import static chess.domain.Fixtures.E8;
import static chess.domain.Fixtures.F1;
import static chess.domain.Fixtures.F2;
import static chess.domain.Fixtures.F7;
import static chess.domain.Fixtures.F8;
import static chess.domain.Fixtures.G1;
import static chess.domain.Fixtures.G2;
import static chess.domain.Fixtures.G7;
import static chess.domain.Fixtures.G8;
import static chess.domain.Fixtures.H1;
import static chess.domain.Fixtures.H2;
import static chess.domain.Fixtures.H7;
import static chess.domain.Fixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("기본 체스 보드를 반환한다.")
    void Given_BoardCreator_When_Create_Then_BasicBoardCreated() {
        //given, when
        Board board = BoardFactory.create();
        Map<Position, Piece> initialPiecePositions = board.getBoard();
        //then
        assertAll(
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                A1, Piece.of(PieceType.ROOK, Color.WHITE),
                                B1, Piece.of(PieceType.KNIGHT, Color.WHITE),
                                C1, Piece.of(PieceType.BISHOP, Color.WHITE),
                                D1, Piece.of(PieceType.QUEEN, Color.WHITE),
                                E1, Piece.of(PieceType.KING, Color.WHITE),
                                F1, Piece.of(PieceType.BISHOP, Color.WHITE),
                                G1, Piece.of(PieceType.KNIGHT, Color.WHITE),
                                H1, Piece.of(PieceType.ROOK, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                A2, Pawn.of(Color.WHITE),
                                B2, Pawn.of(Color.WHITE),
                                C2, Pawn.of(Color.WHITE),
                                D2, Pawn.of(Color.WHITE),
                                E2, Pawn.of(Color.WHITE),
                                F2, Pawn.of(Color.WHITE),
                                G2, Pawn.of(Color.WHITE),
                                H2, Pawn.of(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                A7, Pawn.of(Color.BLACK),
                                B7, Pawn.of(Color.BLACK),
                                C7, Pawn.of(Color.BLACK),
                                D7, Pawn.of(Color.BLACK),
                                E7, Pawn.of(Color.BLACK),
                                F7, Pawn.of(Color.BLACK),
                                G7, Pawn.of(Color.BLACK),
                                H7, Pawn.of(Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                A8, Piece.of(PieceType.ROOK, Color.BLACK),
                                B8, Piece.of(PieceType.KNIGHT, Color.BLACK),
                                C8, Piece.of(PieceType.BISHOP, Color.BLACK),
                                D8, Piece.of(PieceType.QUEEN, Color.BLACK),
                                E8, Piece.of(PieceType.KING, Color.BLACK),
                                F8, Piece.of(PieceType.BISHOP, Color.BLACK),
                                G8, Piece.of(PieceType.KNIGHT, Color.BLACK),
                                H8, Piece.of(PieceType.ROOK, Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void Given_BoardCreator_When_GetPieceFromEmptyPosition_Then_ReturnEmptyPiece() {
        //given
        Board board = BoardFactory.create();
        // when, then
        Map<Position, Piece> initialPiecePositions = board.getBoard();
        Arrays.stream(Rank.values())
                .filter(rank -> !List.of(Rank.ONE, Rank.TWO, Rank.SEVEN, Rank.EIGHT).contains(rank))
                .flatMap(rank -> Arrays.stream(File.values()).map(file -> Position.of(file, rank)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(Piece.EMPTY_PIECE));
    }
}
