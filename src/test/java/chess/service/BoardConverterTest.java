package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.Game;
import chess.domain.game.InitialGameCreator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardConverterTest {

    @DisplayName("보드를 문자열로 변환한다.")
    @Test
    void convertToString() {
        Game game = new Game(new InitialGameCreator());

        String actual = BoardConverter.convertToString(game.board());
        String expected = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("문자열을 보드로 변환한다.")
    @Test
    void convertToBoard() {
        String string = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";

        Map<Position, Piece> actual = BoardConverter.convertToBoard(string);
        Map<Position, Piece> expected = new InitialGameCreator().createBoard();

        assertThat(actual).isEqualTo(expected);
    }
}
