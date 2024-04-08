package chess.domain.game.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.ClearBoardInitializer;
import chess.domain.board.SavedBoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @Test
    @DisplayName("Piece가 이동을 했다면 블랙 팀의 차례를 나타내는 상태를 반환한다.")
    void successMoveThenReturnWhiteTurn() {
        Map<Position, Piece> positionPieces = new HashMap<>();
        positionPieces.put(Position.of(1, 1), new King(Color.WHITE));
        positionPieces.put(Position.of(4, 1), new King(Color.WHITE));
        positionPieces.put(Position.of(4, 2), new Rook(Color.BLACK));
        WhiteTurn whiteTurn = new WhiteTurn(new Board(new SavedBoardInitializer(positionPieces)));

        assertThat(whiteTurn.move(Position.of(4, 1), Position.of(4, 2)))
                .isInstanceOf(BlackTurn.class);
    }

    @Test
    @DisplayName("Piece가 이동을 했고, 왕이 잡혔다면 게임 종료의 상태를 반환한다.")
    void moveAndKingKilledThenReturnFinish() {
        Map<Position, Piece> positionPieces = new HashMap<>();
        positionPieces.put(Position.of(4, 1), new King(Color.BLACK));
        positionPieces.put(Position.of(1, 2), new Rook(Color.WHITE));
        positionPieces.put(Position.of(1, 1), new King(Color.BLACK));
        WhiteTurn whiteTurn = new WhiteTurn(new Board(new SavedBoardInitializer(positionPieces)));

        assertThat(whiteTurn.move(Position.of(1, 2), Position.of(1, 1)))
                .isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("Piece가 이동이 불가능하다면 예외가 발생한다.")
    void canNotMove() {
        Map<Position, Piece> positionPieces = new HashMap<>();
        positionPieces.put(Position.of(1, 2), new Pawn(Color.WHITE));
        positionPieces.put(Position.of(1, 1), new King(Color.BLACK));
        WhiteTurn whiteTurn = new WhiteTurn(new Board(new SavedBoardInitializer(positionPieces)));

        assertThatThrownBy(() -> whiteTurn.move(Position.of(1, 2), Position.of(1, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동이 불가능합니다.");
    }

    @Test
    @DisplayName("현재 차례 조회 시 화이트를 반환한다.")
    void getTurn() {
        WhiteTurn whiteTurn = new WhiteTurn(new Board(new ClearBoardInitializer()));
        assertThat(whiteTurn.getTurn()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("게임 종료를 물어볼 경우 항상 거짓을 반환한다.")
    void isFinished() {
        WhiteTurn whiteTurn = new WhiteTurn(new Board(new ClearBoardInitializer()));
        assertThat(whiteTurn.isFinish()).isFalse();
    }
}
