package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(BoardFactory.createInitialBoard());
    }

    @DisplayName("체스 게임을 시작할 수 있다.")
    @Test
    void start() {
        chessGame.start(Collections.emptyList());

        assertThat(chessGame.isPlaying()).isTrue();
    }

    @DisplayName("체스 게임을 종료할 수 있다.")
    @Test
    void end() {
        chessGame.end();

        assertThat(chessGame.isPlaying()).isFalse();
    }

    @DisplayName("체스 게임을 진행할 수 있다.")
    @Test
    void movePiece() {
        chessGame.start(Collections.emptyList());

        Position source = Position.convert("b2");
        Position target = Position.convert("b3");

        chessGame.move(source, target);

        assertThat(chessGame.isPlaying()).isTrue();
    }
}
