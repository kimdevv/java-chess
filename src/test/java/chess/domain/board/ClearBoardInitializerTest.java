package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClearBoardInitializerTest {

    @Test
    @DisplayName("전체 말들의 초기 위치 정보를 반환한다. - 기물들의 위치 확인")
    void initializeAllPieces() {
        Map<Position, Piece> initialPiecePositions = new ClearBoardInitializer().initialize();
        assertAll(
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 1), new Rook(Color.WHITE),
                                Position.of(2, 1), new Knight(Color.WHITE),
                                Position.of(3, 1), new Bishop(Color.WHITE),
                                Position.of(4, 1), new Queen(Color.WHITE),
                                Position.of(5, 1), new King(Color.WHITE),
                                Position.of(6, 1), new Bishop(Color.WHITE),
                                Position.of(7, 1), new Knight(Color.WHITE),
                                Position.of(8, 1), new Rook(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 2), new Pawn(Color.WHITE),
                                Position.of(2, 2), new Pawn(Color.WHITE),
                                Position.of(3, 2), new Pawn(Color.WHITE),
                                Position.of(4, 2), new Pawn(Color.WHITE),
                                Position.of(5, 2), new Pawn(Color.WHITE),
                                Position.of(6, 2), new Pawn(Color.WHITE),
                                Position.of(7, 2), new Pawn(Color.WHITE),
                                Position.of(8, 2), new Pawn(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 7), new Pawn(Color.BLACK),
                                Position.of(2, 7), new Pawn(Color.BLACK),
                                Position.of(3, 7), new Pawn(Color.BLACK),
                                Position.of(4, 7), new Pawn(Color.BLACK),
                                Position.of(5, 7), new Pawn(Color.BLACK),
                                Position.of(6, 7), new Pawn(Color.BLACK),
                                Position.of(7, 7), new Pawn(Color.BLACK),
                                Position.of(8, 7), new Pawn(Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 8), new Rook(Color.BLACK),
                                Position.of(2, 8), new Knight(Color.BLACK),
                                Position.of(3, 8), new Bishop(Color.BLACK),
                                Position.of(4, 8), new Queen(Color.BLACK),
                                Position.of(5, 8), new King(Color.BLACK),
                                Position.of(6, 8), new Bishop(Color.BLACK),
                                Position.of(7, 8), new Knight(Color.BLACK),
                                Position.of(8, 8), new Rook(Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void initializeEmptyPieces() {
        Map<Position, Piece> initialPiecePositions = new ClearBoardInitializer().initialize();
        IntStream.rangeClosed(3, 6).boxed()
                .flatMap(rank -> IntStream.rangeClosed(1, 8).boxed()
                        .map(file -> Position.of(file, rank)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(
                        Empty.getInstance()));
    }
}
