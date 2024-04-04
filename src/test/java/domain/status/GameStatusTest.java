package domain.status;

import domain.Side;
import domain.board.ChessBoard;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PieceFixture;
import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStatusTest {

    @DisplayName("게임의 현재 상태를 계산한다.")
    @Nested
    class CalculateStatus {

        @DisplayName("블랙 팀이 이긴 상황")
        @Test
        void blackWin() {
            Map<Position, Piece> map = new HashMap<>();
            map.put(PositionFixture.d1(), PieceFixture.whiteQueen());
            map.put(PositionFixture.d2(), PieceFixture.blackKnight());
            map.put(PositionFixture.d4(), PieceFixture.blackQueen());

            ChessBoard chessBoard = new ChessBoard(map);
            GameStatus gameStatus = GameStatus.from(chessBoard);

            assertThat(gameStatus.blackScore()).isEqualTo(11.5);
            assertThat(gameStatus.whiteScore()).isEqualTo(9);
            assertThat(gameStatus.winner()).isEqualTo(Side.BLACK);
        }

        @DisplayName("화이트 팀이 이긴 상황")
        @Test
        void whiteWin() {
            Map<Position, Piece> map = new HashMap<>();
            map.put(PositionFixture.d1(), PieceFixture.blackQueen());
            map.put(PositionFixture.d2(), PieceFixture.whiteKnight());
            map.put(PositionFixture.d4(), PieceFixture.whiteQueen());

            ChessBoard chessBoard = new ChessBoard(map);
            GameStatus gameStatus = GameStatus.from(chessBoard);

            assertThat(gameStatus.blackScore()).isEqualTo(9);
            assertThat(gameStatus.whiteScore()).isEqualTo(11.5);
            assertThat(gameStatus.winner()).isEqualTo(Side.WHITE);
        }

        @DisplayName("무승부 상황")
        @Test
        void draw() {
            Map<Position, Piece> map = new HashMap<>();
            map.put(PositionFixture.a2(), PieceFixture.whiteQueen());
            map.put(PositionFixture.a3(), PieceFixture.whiteKnight());
            map.put(PositionFixture.d2(), PieceFixture.blackKnight());
            map.put(PositionFixture.d4(), PieceFixture.blackQueen());

            ChessBoard chessBoard = new ChessBoard(map);
            GameStatus gameStatus = GameStatus.from(chessBoard);

            assertThat(gameStatus.blackScore()).isEqualTo(11.5);
            assertThat(gameStatus.whiteScore()).isEqualTo(11.5);
            assertThat(gameStatus.winner()).isEqualTo(Side.NONE);
        }
    }
}
