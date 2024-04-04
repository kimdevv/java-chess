package chess.domain.chessgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.gamecommand.GameCommand;
import chess.domain.chessgame.gamecommand.GameElements;
import chess.domain.chessgame.gamecommand.MoveElements;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    @Test
    void 입력받은_움직일_칸과_목적지_칸을_생성한다() {
        GameElements gameElements = new GameElements(GameCommand.MOVE, new MoveElements("b2", "b3"));
        assertAll(
                () -> assertThat(gameElements.createMoveSquare().get(0)).isEqualTo(
                        new Square(Lettering.B, Numbering.TWO)),
                () -> assertThat(gameElements.createMoveSquare().get(1)).isEqualTo(
                        new Square(Lettering.B, Numbering.THREE))
        );
    }

    @Test
    void 입력받은_움직일_칸에_체스_말이_없으면_예외를_발생시킨다() {
        ChessGame chessGame = new ChessGame(ChessBoard.initialBoard());
        List<Square> moveSquares = List.of(
                new Square(Lettering.C, Numbering.THREE), new Square(Lettering.C, Numbering.FOUR)
        );
        assertThatThrownBy(() -> chessGame.executeTurn(moveSquares))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
