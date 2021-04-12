package chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;

public class ChessGameTest {
    @DisplayName("게임 생성 확인")
    @Test
    void create() {
        assertDoesNotThrow(() -> new ChessGame());
    }

    @DisplayName("게임 진행 시 isEnd 거짓 확인")
    @Test
    void isPlaying() {
        ChessGame chessGame = new ChessGame();
        assertFalse(chessGame.isEnd());
    }
}
