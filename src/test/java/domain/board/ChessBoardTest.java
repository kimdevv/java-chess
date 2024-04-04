package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import fixture.PieceFixture;
import fixture.PositionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessBoardTest {

    @DisplayName("source 에서 target 으로 기물을 옮긴다.")
    @Nested
    class MovePiece {

        ChessBoard chessBoard;

        @BeforeEach
        void setUp() {
            chessBoard = new ChessBoard(ChessBoardInitializer.init());
            chessBoard.move(PositionFixture.a2(), PositionFixture.a4());
        }

        @DisplayName("움직인 후 기존 위치의 기물은 제거된다.")
        @Test
        void removePieceAfterMove() {
            assertThatThrownBy(() -> chessBoard.findPiece(PositionFixture.a2()))
                    .isInstanceOf(IllegalArgumentException.class).hasMessage("해당 위치에 기물이 없습니다.");
        }

        @DisplayName("목적지에 기물을 놓는다.")
        @Test
        void putPieceAfterMove() {
            assertThat(chessBoard.findPiece(PositionFixture.a4())).isEqualTo(PieceFixture.whitePawn());
        }
    }

    @DisplayName("왕들의 생사여부를 확인한다.")
    @Nested
    class kingAlive {

        ChessBoard chessBoard;

        @BeforeEach
        void setUp() {
            Map<Position, Piece> kingTestBoard = new LinkedHashMap<>();
            kingTestBoard.put(PositionFixture.a1(), PieceFixture.blackKing());
            kingTestBoard.put(PositionFixture.a2(), PieceFixture.whiteKing());
            chessBoard = new ChessBoard(kingTestBoard);
        }

        @DisplayName("두 왕이 모두 살아있는지 확인한다.")
        @Test
        void allKingsAlive() {
            assertThat(chessBoard.isAllKingAlive()).isTrue();
        }

        @DisplayName("왕이 하나라도 죽었는지 확인한다.")
        @Test
        void oneKingDie() {
            chessBoard.move(PositionFixture.a1(), PositionFixture.a2());

            assertThat(chessBoard.isAllKingAlive()).isFalse();
        }
    }
}
